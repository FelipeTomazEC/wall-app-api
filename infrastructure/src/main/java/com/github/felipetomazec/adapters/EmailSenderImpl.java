package com.github.felipetomazec.adapters;

import com.github.felipetomazec.config.EmailConfig;
import com.github.felipetomazec.events.authorsignup.dependencies.EmailSender;
import com.github.felipetomazec.events.authorsignup.dtos.EmailObjectDTO;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
            MimeMessagePreparator message = mimeMessage -> {
                var helper = new MimeMessageHelper(mimeMessage);
                var from = String.format("🧱 Wall App 🧱 <%s>", config.getUsername());
                helper.setTo(emailObject.getTo());
                helper.setFrom(from);
                helper.setText(emailObject.getContent(), true);
                helper.setSubject(emailObject.getSubject());
            };

            sender.send(message);

            log.info("Email sent to {}", emailObject.getTo());
        } catch (MailException exception) {
            log.error("Error while sending email with subject {} to {}. Details = {}",
                    emailObject.getSubject(),
                    emailObject.getTo(),
                    exception.getMessage()
            );
        }
    }
}
