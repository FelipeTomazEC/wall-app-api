package com.github.felipetomazec.repositories.interfaces;

import com.github.felipetomazec.repositories.entities.AuthorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorJPARepository extends JpaRepository<AuthorJpaEntity, String> {
    Optional<AuthorJpaEntity> getByEmail(String email);
}
