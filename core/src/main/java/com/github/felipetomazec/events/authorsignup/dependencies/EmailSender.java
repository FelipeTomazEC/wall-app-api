package com.github.felipetomazec.events.authorsignup.dependencies;

import com.github.felipetomazec.events.authorsignup.dtos.EmailObjectDTO;

public interface EmailSender {
    void send(EmailObjectDTO emailObject);
}
