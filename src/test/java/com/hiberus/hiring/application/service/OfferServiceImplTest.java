package com.hiberus.hiring.application.service;

import static org.mockito.Mockito.verify;

import com.hiberus.hiring.application.query.BrandQueryService;
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

  @Mock
  private BrandQueryService brandQueryService;

  @InjectMocks
  private OfferServiceImpl offerService;

  private Offer offer;

  @BeforeEach
  void setUp() {
    offer = new Offer();
    offer.setBrandId(1);
  }

  @Test
  @DisplayName("Success: Given an offer, when createOffer, then verify brand and create the offer")
  void givenAnOfferWhenCreateOfferThenVerifyBrandAndCreateThe() {
    Long brandId = Long.valueOf(offer.getBrandId());
    offerService.create(offer);
    verify(brandQueryService).verifyBrand(brandId);
    verify(offerRepository).create(offer);
  }
}