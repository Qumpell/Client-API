package com.example.unit.service;

import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.auth.AuthenticationService;
import com.example.auth.RegisterRequest;
import com.example.auth.mapper.RegisterRequestMapper;
import com.example.configuration.JwtService;
import com.example.model.Client;
import com.example.model.Token;
import com.example.repository.ClientRepository;
import com.example.repository.TokenRepository;
import com.example.service.impl.GenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RegisterRequestMapper registerRequestMapper;

    @Mock
    private GenerationService generationService;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void should_Generate_Token_When_Valid_Register_Request_Was_Sent() {
        // given
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .login("testLogin")
                .password("testPassword")
                .peselNumber("12345678901")
                .birthDate(LocalDate.of(1990, 5, 15))
                .addressSet(new HashSet<>())
                .build();

        Client client = new Client();
        client.setLogin("testLogin");
        client.setPassword("encodedPassword");
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW50ZXN0MSIsImlhdCI6MTcwMDQxNjMwMSwiZXhwIjoxNzAwNDE3NzQxfQ.Ye2JPnzH8KrV6awL7L0NlXpdVxZOjm1Wo8KmQQKlPHc";

        when(registerRequestMapper.fromRegisterRequest(any(), any())).thenReturn(client);
        when(clientRepository.save(any())).thenReturn(client);
        when(generationService.getGenerationOfDate(any())).thenReturn("generation");
        when(jwtService.generateToken(any())).thenReturn(jwtToken);

        // when
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // then
        assertNotNull(response);
        assertEquals(jwtToken, response.getToken());
        verify(registerRequestMapper, times(1)).fromRegisterRequest(registerRequest, passwordEncoder);
        verify(clientRepository, times(1)).save(client);
        verify(generationService, times(1)).getGenerationOfDate(client.getBirthDate());
        verify(jwtService, times(1)).generateToken(client);
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void  should_Return_Token_Given_Valid_Authentication_Request() {
        // given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setLogin("testLogin");
        authenticationRequest.setPassword("testPassword");

        Client client = new Client();
        client.setLogin("testLogin");
        client.setPassword("encodedPassword");
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW50ZXN0MSIsImlhdCI6MTcwMDQxNjMwMSwiZXhwIjoxNzAwNDE3NzQxfQ.Ye2JPnzH8KrV6awL7L0NlXpdVxZOjm1Wo8KmQQKlPHc";

        when(clientRepository.findByLogin("testLogin")).thenReturn(Optional.of(client));
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtService.generateToken(any())).thenReturn(jwtToken);

        // when
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // then
        assertNotNull(response);
        assertEquals(jwtToken, response.getToken());
        verify(clientRepository, times(1)).findByLogin("testLogin");
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtService, times(1)).generateToken(client);
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void should_Return_True_When_Login_Exists() {
        // given
        String login = "testLogin";
        when(clientRepository.findByLogin(login)).thenReturn(Optional.of(new Client()));

        // when
        boolean result = authenticationService.loginExists(login);

        // then
        assertTrue(result);
        verify(clientRepository, times(1)).findByLogin(login);
    }


    @Test
    void should_Return_False_When_Login__Does_Not_Exist() {
        // given
        String login = "testLogin";

        when(clientRepository.findByLogin(login)).thenReturn(Optional.empty());

        // when
        boolean result = authenticationService.loginExists(login);

        // then
        assertFalse(result);
        verify(clientRepository, times(1)).findByLogin(login);
    }
}