package com.hiberus.hiring.application.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.hiberus.hiring.application.command.OfferCommandService;
import com.hiberus.hiring.application.query.BrandQueryService;
import com.hiberus.hiring.application.query.OfferQueryService;
import com.hiberus.hiring.domain.model.Offer;
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
  private BrandQueryService brandQueryService;

  @Mock
  private OfferQueryService offerQueryService;

  @Mock
  private OfferCommandService offerCommandService;

  @InjectMocks
  private OfferServiceImpl offerService;

  private Offer offer;

  @BeforeEach
  void setUp() {
    offer = new Offer();
    offer.setBrandId(1);
  }

  @Test
  @DisplayName("Success: Given an offer, when create, then verify brand, check offer existence, and create the offer")
  void givenAnOfferWhenCreateThenVerifyBrandCheckOfferExistenceAndCreateTheOffer() {
    doNothing().when(brandQueryService).verifyBrand(Long.valueOf(offer.getBrandId()));
    doNothing().when(offerQueryService).verifyAlreadyOfferExists(offer.getOfferId());
    doNothing().when(offerCommandService).create(offer);
    offerService.create(offer);
    verify(brandQueryService).verifyBrand(Long.valueOf(offer.getBrandId()));
    verify(offerQueryService).verifyAlreadyOfferExists(offer.getOfferId());
    verify(offerCommandService).create(offer);
  }

  @Test
  @DisplayName("Success: Given no Offer When delete all, then delete all offers")
  void giveNoOfferWhenDeleteAllThenDeleteAllOffers() {
    doNothing().when(offerCommandService).deleteAll();
    offerService.deleteAll();
    verify(offerCommandService).deleteAll();
  }
}