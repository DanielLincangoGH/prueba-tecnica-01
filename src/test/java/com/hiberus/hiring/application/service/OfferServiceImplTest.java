package com.hiberus.hiring.application.service;

import static org.mockito.Mockito.times;
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
class OfferServiceImplTest {

  @Mock
  private OfferRepository offerRepository;

  @InjectMocks
  private OfferServiceImpl offerService;

  private Offer offer;

  @BeforeEach
  void setUp() {
    offer = new Offer();
  }

  @Test
  @DisplayName("SUCCESS: Given an offer, when createOffer, then create the offer")
  void givenAnOfferWhenCreateOfferThenCreateTheOffer() {
    offerService.createOffer(offer);
    verify(offerRepository, times(1)).create(offer);
  }

}