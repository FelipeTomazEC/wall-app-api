package com.github.felipetomazec.presenters;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.entities.Reaction;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.RetrieveAllPostsPresenter;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.AuthorInfoDTO;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.PartialPostDTO;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.RetrieveAllPostsOutput;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HttpRetrieveAllPostsPresenter implements RetrieveAllPostsPresenter {
    @Override
    public RetrieveAllPostsOutput success(List<Post> posts, List<Author> authors) {
        var authorsInfoMap = authors.stream()
                .map(author -> AuthorInfoDTO.builder()
                        .avatar(author.getProfileImage())
                        .id(author.getId())
                        .username(author.getUsername())
                        .build()
                )
                .collect(Collectors.toMap(
                        AuthorInfoDTO::getId,
                        author -> author,
                        (oldValue, newValue) -> oldValue)
                );

        var postInfoList = posts.stream()
                .map(post -> PartialPostDTO.builder()
                        .id(post.getId())
                        .postedAt(post.getCreatedAt())
                        .author(authorsInfoMap.get(post.getAuthorId()))
                        .content(post.getContent())
                        .numberOfComments(post.getComments().size())
                        .numberOfReactionsByType(getCountOfReactionByType(post.getReactions()))
                        .build()
                ).collect(Collectors.toList());

        return new RetrieveAllPostsOutput(postInfoList);
    }

    private Map<Reaction.Type, Integer> getCountOfReactionByType(Set<Reaction> reactions) {
        return reactions.stream()
                .collect(Collectors.groupingBy(Reaction::getType, Collectors.toList()))
                .entrySet()
                .parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> entry.getValue().size()));
    }
}
