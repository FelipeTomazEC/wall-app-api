package com.github.felipetomazec.usecases.signup.dependencies;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.usecases.signup.dtos.SignUpOutput;

public interface SignUpPresenter {
    SignUpOutput emailAlreadyRegistered(String email);

    SignUpOutput invalidProfileImage();

    SignUpOutput success(Author author);
}
