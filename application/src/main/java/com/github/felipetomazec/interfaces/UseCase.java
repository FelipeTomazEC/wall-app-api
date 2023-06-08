package com.github.felipetomazec.interfaces;

public interface UseCase <Input, Output> {
    Output execute(Input input);
}
