package com.github.felipetomazec.usecases.signup.dependencies;

import com.github.felipetomazec.entities.Author;

public interface SaveAuthorRepository {
    void save(Author author);
}
