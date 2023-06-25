package com.github.felipetomazec.usecases.retrieveallposts.dependencies;

import com.github.felipetomazec.entities.Author;

import java.util.List;
import java.util.Set;

public interface GetAllAuthorsRepository {
    List<Author> getAuthorByIds(Set<String> ids);
}
