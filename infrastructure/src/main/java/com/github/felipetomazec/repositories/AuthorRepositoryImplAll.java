package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.repositories.entities.AuthorJpaEntity;
import com.github.felipetomazec.repositories.interfaces.AuthorJPARepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetAllAuthorsRepository;
import com.github.felipetomazec.usecases.signup.dependencies.GetAuthorRepository;
import com.github.felipetomazec.usecases.signup.dependencies.SaveAuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImplAll implements SaveAuthorRepository, GetAuthorRepository, GetAllAuthorsRepository {

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

    @Override
    public List<Author> getAuthorByIds(Set<String> ids) {
        return repository.findAllById(ids).stream()
                .map(AuthorJpaEntity::to)
                .collect(Collectors.toList());
    }
}
