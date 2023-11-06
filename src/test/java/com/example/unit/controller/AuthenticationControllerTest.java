package com.example.unit.controller;

import com.example.auth.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void should_Return_OK_Status_When_Client_Is_Successfully_Registered() {
        RegisterRequest request = new RegisterRequest();
        request.setLogin("test");
        request.setPassword("password");

        when(authenticationService.loginExists(request.getLogin())).thenReturn(false);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void should_Return_CONFLICT_Status_When_Client_Login_Already_Exists() {
        RegisterRequest request = new RegisterRequest();
        request.setLogin("test");
        request.setPassword("password");

        when(authenticationService.loginExists(request.getLogin())).thenReturn(true);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(request);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void should_Return_OK_Status_When_Client_Is_Successfully_Authenticated() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setLogin("test");
        request.setPassword("password");

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //TODO
//    @Test
//    public void should_Return_Not_Found_Status_When_Client_Was_Not_Found_By_Login() {
//        AuthenticationRequest request = new AuthenticationRequest();
//        request.setLogin("test");
//        request.setPassword("password");
//
//        when(authenticationService.authenticate(request)).thenThrow(EntityNotFoundException.class);
//
//        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(request);
//
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    }
}