package com.github.felipetomazec.events.authorsignup;

import com.github.felipetomazec.events.authorsignup.dependencies.EmailSender;
import com.github.felipetomazec.events.authorsignup.dtos.EmailObjectDTO;
import com.github.felipetomazec.interfaces.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignedUpEmailNotificationHandler implements EventHandler<AuthorSignedUpEvent> {
    private final EmailSender emailSender;
    private static final String EMAIL_SUBJECT = "Welcome to the Wall App";

    public void handle(AuthorSignedUpEvent event) {
        var content = String.format("Hi %s! Welcome to the Wall App.", event.name());

        var emailObject = EmailObjectDTO.builder()
                .to(event.email())
                .subject(EMAIL_SUBJECT)
                .content(content)
                .build();

        emailSender.send(emailObject);
    }
}
