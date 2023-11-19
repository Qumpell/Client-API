package com.example.auth;

import com.example.auth.mapper.RegisterRequestMapper;
import com.example.configuration.JwtService;
import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.exception.EntityNotFoundException;
import com.example.model.Client;
import com.example.model.Role;
import com.example.model.Token;
import com.example.model.TokenType;
import com.example.repository.ClientRepository;
import com.example.repository.TokenRepository;
import com.example.service.impl.GenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterRequestMapper registerRequestMapper;
    private final GenerationService generationService;
    private final TokenRepository tokenRepository;
    public AuthenticationResponse register(RegisterRequest request) {
        var client = registerRequestMapper.fromRegisterRequest(request, passwordEncoder);
        client.setRole(Role.USER);
        client.setGeneration(generationService.getGenerationOfDate(client.getBirthDate()));
        var savedClient = clientRepository.save(client);
        var jwtToken = jwtService.generateToken(client);
        saveClientToken(savedClient, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var client = clientRepository.findByLogin(request.getLogin())
                .orElseThrow(
                        () ->
                                new EntityNotFoundException("Client not found with login" + request.getLogin(),
                                        request.getLogin()));
        var jwtToken = jwtService.generateToken(client);
        revokeAllClientTokens(client);
        saveClientToken(client, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
   private void revokeAllClientTokens(Client client){
        var validClientTokens = tokenRepository.findAllValidTokensByClient(client.getLogin());
        if(validClientTokens.isEmpty()){
            return;
        }
        validClientTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validClientTokens);
   }

    private void saveClientToken(Client client, String jwtToken) {
        var token = Token.builder()
                .client(client)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }


    public boolean loginExists(String login){
        Optional<Client> clientOptional = clientRepository.findByLogin(login);
        return clientOptional.isPresent();
    }
}
