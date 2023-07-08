package com.github.felipetomazec.adapters;

import com.github.felipetomazec.events.authorsignup.dependencies.WelcomeEmailGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class ThymeLeafWelcomeEmailGenerator implements WelcomeEmailGenerator {

    private final TemplateEngine templateEngine;

    @Override
    public String generate(String recipientName) {
        var context = new Context();
        context.setVariable("recipientName", recipientName);

        return templateEngine.process("welcome-email", context);
    }
}
