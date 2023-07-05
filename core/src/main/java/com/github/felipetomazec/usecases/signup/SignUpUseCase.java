package com.github.felipetomazec.usecases.signup;

import com.github.felipetomazec.entities.Author;
import com.github.felipetomazec.events.AuthorSignedUpEvent;
import com.github.felipetomazec.interfaces.EventPublisher;
import com.github.felipetomazec.interfaces.UseCase;
import com.github.felipetomazec.usecases.signup.dependencies.GetAuthorRepository;
import com.github.felipetomazec.usecases.signup.dependencies.SaveAuthorRepository;
import com.github.felipetomazec.usecases.signup.dependencies.ImageUploader;
import com.github.felipetomazec.usecases.signup.dependencies.SignUpPresenter;
import com.github.felipetomazec.usecases.signup.dtos.SignUpInput;
import com.github.felipetomazec.usecases.signup.dtos.SignUpOutput;
import com.github.felipetomazec.usecases.signup.exceptions.InvalidImageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpUseCase implements UseCase<SignUpInput, SignUpOutput> {

    private final SaveAuthorRepository saveAuthorRepository;
    private final GetAuthorRepository getAuthorRepository;
    private final ImageUploader imageUploader;
    private final SignUpPresenter presenter;
    private final EventPublisher<AuthorSignedUpEvent> eventPublisher;

    @Override
    public SignUpOutput execute(SignUpInput input) {
        var email = input.email();
        var username = input.username();

        var isAuthorAlreadyRegistered = getAuthorRepository.getAuthorByEmail(email).isPresent();

        if (isAuthorAlreadyRegistered) {
            return presenter.emailAlreadyRegistered(input.email());
        }

        try {
            var profileImageURL = imageUploader.upload(input.profileImage(), "profile-images");
            var id = UUID.randomUUID();
            var author = Author.builder()
                    .id(id)
                    .profileImage(profileImageURL.toExternalForm())
                    .username(username)
                    .email(email)
                    .build();

            saveAuthorRepository.save(author);

            eventPublisher.publish(new AuthorSignedUpEvent(username,email));

            return presenter.success(author);
        } catch (InvalidImageException exception) {
            return presenter.invalidProfileImage();
        }
    }
}
