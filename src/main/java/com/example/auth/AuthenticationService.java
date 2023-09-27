package com.example.auth;

import com.example.configuration.JwtService;
import com.example.exception.EntityNotFoundException;
import com.example.model.Client;
import com.example.model.Role;
import com.example.repository.ClientRepository;
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
    public AuthenticationResponse register(RegisterRequest request) {
            var client = Client.builder()
                .name(request.getFirstname())
                .surname(request.getLastname())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                    .peselNumber(request.getPeselNumber())
                    .birthDate(request.getBirthDate())
                    .role(Role.USER)
                .build();
            clientRepository.save(client);
            var jwtToken = jwtService.generateToken(client);
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
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public boolean loginExists(String login){
        Optional<Client> clientOptional = clientRepository.findByLogin(login);
        return clientOptional.isPresent();
    }
}
