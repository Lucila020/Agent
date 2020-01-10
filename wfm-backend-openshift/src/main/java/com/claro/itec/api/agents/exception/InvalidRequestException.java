package com.claro.itec.api.agents.exception;

import lombok.Getter;

@Getter
public class InvalidRequestException extends BaseApiException {

    private final String errorCode = "DEMO_ERROR_01";

    public InvalidRequestException(String message) {
        super(message);
    }

}
