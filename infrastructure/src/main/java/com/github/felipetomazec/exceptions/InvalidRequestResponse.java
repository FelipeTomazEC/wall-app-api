package com.github.felipetomazec.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InvalidRequestResponse {
    private List<String> errors;
}
