package com.github.felipetomazec.presenters;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.usecases.signup.dependencies.SignUpPresenter;
import com.github.felipetomazec.usecases.signup.dtos.SignUpOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class HttpSignUpPresenter implements SignUpPresenter {

    @Override
    public SignUpOutput emailAlreadyRegistered(String email) {
        var message = String.format("Email %s is already registered.", email);
        throw new ResponseStatusException(HttpStatus.CONFLICT, message);
    }

    @Override
    public SignUpOutput invalidProfileImage() {
        var message = "Field 'profileImage' must be a valid Base64 encoded image.";
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    @Override
    public SignUpOutput success(Author author) {
        return new SignUpOutput(author.getId().toString());
    }
}
