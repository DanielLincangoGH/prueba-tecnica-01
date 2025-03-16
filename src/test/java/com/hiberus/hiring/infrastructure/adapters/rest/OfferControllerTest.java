package com.hiberus.hiring.infrastructure.adapters.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.in.OfferService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private OfferService offerService;

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("Success: Given a valid offer, when createNewOffer is called, then it returns status 201 Created")
  public void givenOfferWhenCreateThenReturnsOk() throws Exception {
    Offer validOffer = Offer.builder()
        .offerId(1L)
        .brandId(1)
        .startDate("2023-01-01T00:00:00Z")
        .endDate("2023-12-31T23:59:59Z")
        .priceListId(1L)
        .productPartnumber("123456789012")
        .priority(1)
        .price(new BigDecimal("10.00"))
        .currencyIso("USD")
        .build();

    mockMvc.perform(post("/offer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(validOffer)))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Failure: Given an invalid offer, when createNewOffer is called, then it returns status 400 Bad Request")
  public void givenInvalidOfferWhenCreateThenReturnBadRequest() throws Exception {
    Offer invalidOffer = Offer.builder()
        .offerId(1L)
        .brandId(1)
        .startDate("2023-01-01T00:00:00Z")
        .endDate("2023-12-31T23:59:59Z")
        .priceListId(1L)
        .productPartnumber("123456789012345") // Invalid partnumber (too long)
        .priority(1)
        .price(new BigDecimal("0.00")) // Invalid price (must be greater than 0)
        .currencyIso("INVALID") // Invalid currency
        .build();

    mockMvc.perform(post("/offer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(invalidOffer)))
        .andExpect(status().isBadRequest());
  }
}