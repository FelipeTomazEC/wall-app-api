package com.github.felipetomazec.usecases.retrieveallposts.dependencies;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.RetrieveAllPostsOutput;

import java.util.List;
import java.util.Map;

public interface RetrieveAllPostsPresenter {
    RetrieveAllPostsOutput success(Map<Post, Long> postsWithCountOfComments, List<Author> authors);
}
