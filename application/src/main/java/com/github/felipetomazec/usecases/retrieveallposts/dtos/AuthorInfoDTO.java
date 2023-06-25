package com.github.felipetomazec.usecases.retrieveallposts.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorInfoDTO {
    private String id;
    private String avatar;
    private String username;
}
