package com.github.felipetomazec.usecases.retrieveallposts.dependencies;

import com.github.felipetomazec.entities.Post;

import java.util.List;

public interface GetAllPostsRepository {
    List<Post> getAll();
}
