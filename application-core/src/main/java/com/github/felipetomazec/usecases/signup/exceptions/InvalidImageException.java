package com.github.felipetomazec.usecases.signup.exceptions;

public class InvalidImageException extends IllegalArgumentException {
    public InvalidImageException(String imageName) {
        super(String.format("%s is not a valid base64 encoded image.", imageName));
    }
}
