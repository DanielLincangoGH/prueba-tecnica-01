package com.hiberus.hiring.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.application.command.OfferCommandService;
import com.hiberus.hiring.application.query.BrandQueryService;
import com.hiberus.hiring.application.query.OfferQueryService;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import java.util.Collections;
import java.util.List;
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

  @Test
  @DisplayName("Success: Given Offer ID, When deleteById is called, then delete the offer by ID")
  void givenOfferIdWhenDeleteByIdIsCalledThenDeleteTheOfferById() {
    Long offerId = 1L;
    doNothing().when(offerQueryService).verifyOfferExists(offerId);
    doNothing().when(offerCommandService).deleteById(offerId);
    offerService.deleteById(offerId);
    verify(offerQueryService).verifyOfferExists(offerId);
    verify(offerCommandService).deleteById(offerId);
  }

  @Test
  @DisplayName("Success: Given no offer, when find all offers, then returns offers")
  void givenNoOfferWhenFindAllOffersThenReturnsOffers() {
    List<Offer> expectedOffers = Collections.singletonList(new Offer());
    when(offerQueryService.findAll()).thenReturn(expectedOffers);
    List<Offer> actualOffers = offerService.findAll();
    assertEquals(expectedOffers, actualOffers);
  }

  @Test
  @DisplayName("Success: Given offer ID, when offer exists, then return the offer")
  void givenOfferIdWhenOfferExistsThenReturnOffer() {
    Long offerId = 1L;
    Offer expectedOffer = new Offer();
    when(offerQueryService.findById(offerId)).thenReturn(expectedOffer);
    Offer actualOffer = offerService.findById(offerId);
    assertEquals(expectedOffer, actualOffer);
  }

  @Test
  @DisplayName("Success: Given part number and brand ID, when offers exist, then return offers")
  void givenPartNumberAndBrandIdWhenOffersExistThenReturnOffers() {
    String partNumber = "12345";
    Long brandId = 1L;
    List<OfferByPartNumber> expectedOffers = Collections.singletonList(new OfferByPartNumber());
    when(offerQueryService.findByPartNumberAndBrand("000000012345", brandId)).thenReturn(expectedOffers);
    List<OfferByPartNumber> actualOffers = offerService.findByPartNumberAndBrand(partNumber, brandId);
    assertEquals(expectedOffers, actualOffers);
  }
}