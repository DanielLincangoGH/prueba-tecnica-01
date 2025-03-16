package com.hiberus.hiring.application.command.impl;

import static org.mockito.Mockito.verify;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferCommandServiceImplTest {

  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private OfferCommandServiceImpl offerCommandService;

  private Offer offer;

  @BeforeEach
  void setUp() {
    offer = new Offer();
    // Initialize the offer object with necessary data
  }

  @Test
  @DisplayName("Success: Given Offer, When create is called, then create the offer")
  void givenOffWhenCreateIsCalledThenCreateTheOffer() {
    offerCommandService.create(offer);
    verify(offerRepository).create(offer);
  }


  @Test
  @DisplayName("Success: Given No Info When delete all, then delete all offers")
  void givenNoInfoWhenDeleteAllThenDeleteAllOffers() {
    offerCommandService.deleteAll();
    verify(offerRepository).deleteAll();
  }
}
