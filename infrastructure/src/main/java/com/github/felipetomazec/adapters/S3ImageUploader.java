package com.github.felipetomazec.adapters;

import com.github.felipetomazec.usecases.signup.dependencies.ImageUploader;
import com.github.felipetomazec.usecases.signup.exceptions.InvalidImageException;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class S3ImageUploader implements ImageUploader {
    @Override
    public URL upload(String base64) throws InvalidImageException {
        try {
            return new URL("https", "someimage.com", "user.jpg");
        } catch (MalformedURLException e) {
            throw new InvalidImageException("image");
        }
    }
}
