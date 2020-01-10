package com.claro.itec.api.agents.exception;

import lombok.Getter;

@Getter
public class NoDataFoundException extends BaseApiException {

    private final String errorCode = "DEMO_ERROR_02";

    public NoDataFoundException(String message) {
        super(message);
    }

}
