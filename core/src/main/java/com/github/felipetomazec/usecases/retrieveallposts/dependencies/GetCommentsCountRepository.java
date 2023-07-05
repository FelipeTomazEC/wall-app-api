package com.github.felipetomazec.usecases.retrieveallposts.dependencies;

import com.github.felipetomazec.entities.Post;

import java.util.List;
import java.util.Map;

public interface GetCommentsCountRepository {
    Map<Post, Long> getCommentsCount(List<Post> posts);
}
