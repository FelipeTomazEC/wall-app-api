package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.repositories.entities.AuthorJpaEntity;
import com.github.felipetomazec.repositories.interfaces.AuthorJPARepository;
import com.github.felipetomazec.usecases.signup.dependencies.GetAuthorRepository;
import com.github.felipetomazec.usecases.signup.dependencies.SaveAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements SaveAuthorRepository, GetAuthorRepository {

    private final AuthorJPARepository repository;

    @Override
    public Optional<Author> getAuthorByEmail(String email) {
        return repository.getByEmail(email)
                .map(AuthorJpaEntity::to);
    }

    @Override
    public void save(Author author) {
        repository.save(AuthorJpaEntity.from(author));
    }
}
