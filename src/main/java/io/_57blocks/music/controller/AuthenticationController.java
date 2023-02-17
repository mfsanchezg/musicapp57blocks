package io._57blocks.music.controller;

import io._57blocks.music.dto.AuthenticationRequest;
import io._57blocks.music.dto.AuthenticationResponse;
import io._57blocks.music.dto.RegisterRequest;
import io._57blocks.music.exceptions.EmailAlreadyExistException;
import io._57blocks.music.exceptions.EmailNotExistException;
import io._57blocks.music.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest) throws EmailAlreadyExistException { //try {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest authenticationRequest
    ) throws EmailNotExistException {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
