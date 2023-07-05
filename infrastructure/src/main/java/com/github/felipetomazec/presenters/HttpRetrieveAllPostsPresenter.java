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
    public RetrieveAllPostsOutput success(Map<Post, Long> postsWithNumberOfComments, List<Author> authors) {
        var authorsInfoMap = authors.stream()
                .map(author -> AuthorInfoDTO.builder()
                        .avatar(author.getProfileImage())
                        .id(author.getId().toString())
                        .username(author.getUsername())
                        .build()
                )
                .collect(Collectors.toMap(
                        AuthorInfoDTO::getId,
                        author -> author,
                        (oldValue, newValue) -> oldValue)
                );

        var postInfoList = postsWithNumberOfComments.entrySet()
                .stream()
                .map(entry -> {
                            var post = entry.getKey();
                            return PartialPostDTO.builder()
                                    .id(post.getId().toString())
                                    .postedAt(post.getCreatedAt())
                                    .author(authorsInfoMap.get(post.getAuthorId().toString()))
                                    .content(post.getContent())
                                    .numberOfComments(entry.getValue())
                                    .numberOfReactionsByType(getCountOfReactionByType(post.getReactions()))
                                    .build();
                        }
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
