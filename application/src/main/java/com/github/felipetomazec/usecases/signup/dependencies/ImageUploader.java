package com.github.felipetomazec.usecases.signup.dependencies;

import com.github.felipetomazec.usecases.signup.exceptions.InvalidImageException;

import java.net.URL;

public interface ImageUploader {
    URL upload(String base64) throws InvalidImageException;
}
