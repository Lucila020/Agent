package com.claro.itec.api.agents.exception;

import com.claro.itec.api.agents.dto.ErrorDetailDTO;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
class ExceptionHandlerAdvice {

    private static final Logger LOGGER = getLogger(ExceptionHandlerAdvice.class.getName());

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorDetailDTO> handleException(InvalidRequestException e) {
        LOGGER.error("Error while receiving message: ", e);
      return new ResponseEntity<>(ErrorDetailDTO.getInstance(e.getErrorCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorDetailDTO> handleException(NoDataFoundException e) {
        LOGGER.error("Error while receiving message: ", e);
        return new ResponseEntity<>(ErrorDetailDTO.getInstance(e.getErrorCode(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
