package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {
    Optional<Credentials> getByEmail(String email);
}
