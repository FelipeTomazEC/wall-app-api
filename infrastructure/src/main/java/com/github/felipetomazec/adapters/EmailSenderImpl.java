package com.github.felipetomazec.adapters;

import com.github.felipetomazec.config.EmailConfig;
import com.github.felipetomazec.events.authorsignup.dependencies.EmailSender;
import com.github.felipetomazec.events.authorsignup.dtos.EmailObjectDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender sender;
    private final EmailConfig config;

    @Override
    public void send(EmailObjectDTO emailObject) {
        log.info("Sending email to {}", emailObject.getTo());
        try {
            var message = new SimpleMailMessage();
            message.setFrom(config.getUsername());
            message.setTo(emailObject.getTo());
            message.setSubject(emailObject.getSubject());
            message.setText(emailObject.getContent());
            sender.send(message);
        } catch (MailException exception) {
            log.error("Error while sending email with subject {} to {}. Details = {}",
                    emailObject.getSubject(),
                    emailObject.getTo(),
                    exception.getMessage()
            );
        }

        log.info("Email sent to {}", emailObject.getTo());
    }
}
