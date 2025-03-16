package com.hiberus.hiring.infrastructure.adapters.exception;

import com.hiberus.hiring.domain.exception.InvalidBrandException;
import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.model.Message;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Message handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage()));
    List<String> messages = errors.entrySet().stream()
        .map(entry -> entry.getKey() + ": " + entry.getValue())
        .toList();
    return Message.builder().code("VALIDATION_DATA")
        .message("Invalid params").messages(messages).build();
  }

  @ExceptionHandler(InvalidBrandException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Message handleInvalidBrandException(InvalidBrandException e) {
    return Message.builder().code("INVALID_BRAND").message(e.getMessage()).build();
  }

  @ExceptionHandler(OfferAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Message handleOfferAlreadyExistsException(OfferAlreadyExistsException e) {
    return Message.builder().code("OFFER_ALREADY_EXISTS").message(e.getMessage()).build();
  }

}
