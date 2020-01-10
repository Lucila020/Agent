package com.claro.itec.api.agents.exception;

import lombok.Getter;

@Getter
public class BaseApiException extends Exception {

    public BaseApiException(String message) {
        super(message);
    }
}
