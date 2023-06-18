package com.github.felipetomazec.services;

import com.github.felipetomazec.dtos.AuthenticationRequest;
import com.github.felipetomazec.dtos.AuthenticationResponse;
import com.github.felipetomazec.dtos.SignUpHttpRequest;
import com.github.felipetomazec.entities.Credentials;
import com.github.felipetomazec.repositories.CredentialsRepository;
import com.github.felipetomazec.usecases.signup.SignUpUseCase;
import com.github.felipetomazec.usecases.signup.dtos.SignUpInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class CredentialsService {

    private final CredentialsRepository repository;
    private final SignUpUseCase useCase;
    private final PasswordEncoder encoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(SignUpHttpRequest request) {
        var input = new SignUpInput(
                request.name(),
                request.email(),
                request.profileImageBase64()
        );

        var output = useCase.execute(input);
        var authorCredentials = Credentials.builder()
                .id(output.newAuthorId())
                .password(encoder.encode(request.password()))
                .email(request.email())
                .build();

        repository.save(authorCredentials);

        var token = generateToken(authorCredentials);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse signIn(AuthenticationRequest request) {
        var email = request.email();
        var user = repository
                .getByEmail(email)
                .orElseThrow(() -> {
                    var message = String.format("User with email %s not found.", email);
                    return new UsernameNotFoundException(message);
                });

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, request.password()));

        var token = generateToken(user);

        return new AuthenticationResponse(token);
    }

    private String generateToken(Credentials credentials) {
        var extraClaims = new HashMap<String, Object>();
        extraClaims.put("authorId", credentials.getId());

        return jwtService.generateToken(credentials, extraClaims);
    }
}
