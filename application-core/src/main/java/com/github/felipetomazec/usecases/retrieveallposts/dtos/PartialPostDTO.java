package com.github.felipetomazec.usecases.retrieveallposts.dtos;

import com.github.felipetomazec.entities.Reaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
public class PartialPostDTO {
    private String id;
    private AuthorInfoDTO author;
    private String content;
    private Map<Reaction.Type, Integer> numberOfReactionsByType;
    private Integer numberOfComments;
    private LocalDateTime postedAt;
}
