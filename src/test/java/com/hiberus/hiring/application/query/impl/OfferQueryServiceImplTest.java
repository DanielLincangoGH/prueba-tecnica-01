package com.hiberus.hiring.application.query.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.exception.OfferNotFoundException;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.domain.ports.OfferRepository;
import java.util.Collections;
import java.util.List;
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

  @Test
  @DisplayName("Failed: Given Offer Id When offer does not exist Then Throws OfferNotFoundException")
  void givenOfferIdWhenOfferDoesNotExistThenThrowsOfferNotFoundException() {
    when(offerRepository.findById(offerId)).thenReturn(Optional.empty());
    assertThrows(OfferNotFoundException.class,
        () -> offerQueryService.verifyOfferExists(offerId));
  }


  @Test
  @DisplayName("Success: Given offer id, when offer exists, then not throws exception")
  void givenOfferIdWhenOfferExistsThenNoThrowsException() {
    when(offerRepository.findById(offerId)).thenReturn(Optional.of(new Offer()));
    offerQueryService.verifyOfferExists(offerId);
  }

  @Test
  @DisplayName("Success: Given no offer, when find all offers, then returns offers")
  void givenNoOfferWhenFindAllOffersThenReturnsOffers() {
    List<Offer> expectedOffers = Collections.singletonList(new Offer());
    when(offerRepository.findAll()).thenReturn(expectedOffers);
    List<Offer> actualOffers = offerQueryService.findAll();
    assertEquals(expectedOffers, actualOffers);
  }

  @Test
  @DisplayName("Success: Given offer ID, when offer exists, then return the offer")
  void givenOfferIdWhenOfferExistsThenReturnOffer() {
    Offer expectedOffer = new Offer();
    when(offerRepository.findById(offerId)).thenReturn(Optional.of(expectedOffer));
    Offer actualOffer = offerQueryService.findById(offerId);
    assertEquals(expectedOffer, actualOffer);
  }

  @Test
  @DisplayName("Failed: Given offer ID, when offer does not exist, then throw OfferNotFoundException")
  void givenOfferIdWhenOfferDoesNotExistThenThrowOfferNotFoundException() {
    when(offerRepository.findById(offerId)).thenReturn(Optional.empty());
    assertThrows(OfferNotFoundException.class, () -> offerQueryService.findById(offerId));
  }

  @Test
  @DisplayName("Success: Given part number and brand ID, when offers exist, then return offers")
  void givenPartNumberAndBrandIdWhenOffersExistThenReturnOffers() {
    String partNumber = "12345";
    Long brandId = 1L;
    List<OfferByPartNumber> expectedOffers = Collections.singletonList(new OfferByPartNumber());
    when(offerRepository.findByPartNumberAndBrand(partNumber, brandId)).thenReturn(expectedOffers);
    List<OfferByPartNumber> actualOffers = offerQueryService.findByPartNumberAndBrand(partNumber, brandId);
    assertEquals(expectedOffers, actualOffers);
  }
}
