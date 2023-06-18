package com.github.felipetomazec.usecases.signup.dependencies;

import com.github.felipetomazec.entities.Author;

import java.util.Optional;

public interface GetAuthorRepository {
    Optional<Author> getAuthorByEmail(String email);
}
