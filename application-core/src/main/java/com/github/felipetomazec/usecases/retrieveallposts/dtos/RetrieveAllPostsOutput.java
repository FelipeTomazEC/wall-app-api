package com.github.felipetomazec.usecases.retrieveallposts.dtos;

import java.util.List;

public record RetrieveAllPostsOutput(List<PartialPostDTO> posts) {
}
