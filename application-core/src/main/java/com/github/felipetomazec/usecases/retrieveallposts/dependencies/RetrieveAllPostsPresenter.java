package com.github.felipetomazec.usecases.retrieveallposts.dependencies;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.RetrieveAllPostsOutput;

import java.util.List;

public interface RetrieveAllPostsPresenter {
    RetrieveAllPostsOutput success(List<Post> posts, List<Author> authors);
}
