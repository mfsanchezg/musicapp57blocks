package io._57blocks.music.services;

import io._57blocks.music.config.JwtService;
import io._57blocks.music.dto.AuthenticationRequest;
import io._57blocks.music.dto.AuthenticationResponse;
import io._57blocks.music.dto.RegisterRequest;
import io._57blocks.music.entities.Role;
import io._57blocks.music.entities.User;
import io._57blocks.music.exceptions.EmailAlreadyExistException;
import io._57blocks.music.exceptions.EmailNotExistException;
import io._57blocks.music.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) throws EmailAlreadyExistException {

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new EmailAlreadyExistException("Email " + registerRequest.getEmail() + " already exists.");
        }

        var user = User.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .message("User " + user.getEmail() + " created successfully.")
                //.token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws EmailNotExistException{

        if(userRepository.findByEmail(authenticationRequest.getEmail()).isEmpty()){
            throw new EmailNotExistException("Email " + authenticationRequest.getEmail() + " not exists.");
        }

       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        log.info("User " + user.getEmail() + " logged successfully.");

        return AuthenticationResponse.builder()
                .message("User " + user.getEmail() + " logged successfully")
                .token(jwtToken)
                .build();
    }
}
