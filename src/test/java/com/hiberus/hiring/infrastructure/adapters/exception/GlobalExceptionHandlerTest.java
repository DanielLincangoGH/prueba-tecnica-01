package com.hiberus.hiring.infrastructure.adapters.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.exception.InvalidBrandException;
import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.exception.OfferNotFoundException;
import com.hiberus.hiring.domain.model.Message;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  void setUp() {
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  @DisplayName("Success: Given MethodArgumentNotValidException, when handle, then return detail message")
  void givenMethodArgumentNotValidExceptionWhenHandleThenReturnDetailMessage() {
    BindingResult bindingResult = mock(BindingResult.class);
    FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
    when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
    Message response = globalExceptionHandler.handleMethodArgumentNotValidException(ex);
    assertEquals("VALIDATION_DATA", response.getCode());
    assertEquals("Invalid params", response.getMessage());
    assertEquals(List.of("field: defaultMessage"), response.getMessages());
  }

  @Test
  @DisplayName("Success: Given InvalidBrandException, when handle, then return detail message")
  void givenInvalidBrandExceptionWhenHandleThenReturnDetailMessage() {
    InvalidBrandException ex = new InvalidBrandException("Invalid brand");
    Message response = globalExceptionHandler.handleInvalidBrandException(ex);
    assertEquals("INVALID_BRAND", response.getCode());
    assertEquals("Invalid brand", response.getMessage());
  }

  @Test
  @DisplayName("Success: Given OfferAlreadyExistsException, when handle, then return detail message")
  void givenOfferAlreadyExistsExceptionWhenHandleThenReturnDetailMessage() {
    OfferAlreadyExistsException ex = new OfferAlreadyExistsException("Offer already exists");
    Message response = globalExceptionHandler.handleOfferAlreadyExistsException(ex);
    assertEquals("OFFER_ALREADY_EXISTS", response.getCode());
    assertEquals("Offer already exists", response.getMessage());
  }

  @Test
  @DisplayName("Success: Given OfferNotFoundException, when handle, then return detail message")
  void givenOfferNotFoundExceptionWhenHandleThenReturnDetailMessage() {
    OfferNotFoundException ex = new OfferNotFoundException("Offer not found");
    Message response = globalExceptionHandler.handleOfferNotFoundException(ex);
    assertEquals("OFFER_NOT_FOUND", response.getCode());
    assertEquals("Offer not found", response.getMessage());
  }
}
