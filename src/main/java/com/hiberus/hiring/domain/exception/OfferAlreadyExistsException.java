package com.hiberus.hiring.domain.exception;

public class OfferAlreadyExistsException extends RuntimeException {

  public OfferAlreadyExistsException(String message) {
    super(message);
  }

}
