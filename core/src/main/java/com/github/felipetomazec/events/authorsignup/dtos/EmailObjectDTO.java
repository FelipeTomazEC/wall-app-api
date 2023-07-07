package com.github.felipetomazec.events.authorsignup.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailObjectDTO {
        private String to;
        private String subject;
        private String content;
}
