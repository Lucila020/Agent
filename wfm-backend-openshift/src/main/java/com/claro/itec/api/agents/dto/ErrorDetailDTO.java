package com.claro.itec.api.agents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetailDTO {

    private String errorCode;
    private String message;

    public static ErrorDetailDTO getInstance(String errorCode, String message) {
        return new ErrorDetailDTO(errorCode, message);
    }

}
