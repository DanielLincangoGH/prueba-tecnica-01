package com.hiberus.hiring.infrastructure.adapters.exception;

import com.hiberus.hiring.domain.exception.InvalidBrandException;
import com.hiberus.hiring.domain.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidBrandException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Message handleInvalidBrandException(InvalidBrandException e) {
    return Message.builder().code("INVALID_BRAND").message(e.getMessage()).build();
  }

}
