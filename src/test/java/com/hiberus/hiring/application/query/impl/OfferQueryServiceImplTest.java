package com.hiberus.hiring.application.query.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferQueryServiceImplTest {

  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private OfferQueryServiceImpl offerQueryService;

  private Long offerId;

  @BeforeEach
  void setUp() {
    offerId = 1L;
  }

  @Test
  @DisplayName("Failed: Given Offer Id When offer exists Then Throws OfferAlreadyExistsException")
  void givenOfferIdWhenOfferExistsThenThrowsOfferAlreadyExistsException() {
    when(offerRepository.findById(offerId)).thenReturn(Optional.of(new Offer()));
    assertThrows(OfferAlreadyExistsException.class,
        () -> offerQueryService.verifyAlreadyOfferExists(offerId));
  }

  @Test
  @DisplayName("Success: Given offer id When not exists Then not throws exception")
  void givenOfferIdWhenNotExistsThenNoThrowsException() {
    when(offerRepository.findById(offerId)).thenReturn(Optional.empty());
    offerQueryService.verifyAlreadyOfferExists(offerId);
  }
}
