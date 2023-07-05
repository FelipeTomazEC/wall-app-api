package com.github.felipetomazec.repositories.entities;


import com.github.felipetomazec.entities.Author;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class AuthorJpaEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String profileImageURL;

    public static AuthorJpaEntity from(Author author) {
        return AuthorJpaEntity.builder()
                .email(author.getEmail())
                .id(author.getId().toString())
                .username(author.getUsername())
                .profileImageURL(author.getProfileImage())
                .build();
    }

    public Author to() {
        return Author.builder()
                .id(UUID.fromString(id))
                .username(username)
                .profileImage(profileImageURL)
                .email(email)
                .build();
    }
}
