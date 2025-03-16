package com.hiberus.hiring.infrastructure.adapters.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.in.OfferService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
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

  @Test
  @DisplayName("Success: Given no offer When delete all offers then it returns status 200 OK")
  public void givenNoOfferWhenDeleteAllOffersThenReturnsOk() throws Exception {
    mockMvc.perform(delete("/offer"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Success: Given offer ID, when deleteOfferById is called, then it returns status 200 OK")
  public void givenOfferIdWhenDeleteOfferByIdThenReturnsOk() throws Exception {
    Long offerId = 1L;
    mockMvc.perform(delete("/offer/{id}", offerId))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Success: Given no offer, when getAllOffers is called, then it returns status 200 OK and the list of offers")
  public void givenNoOfferWhenGetAllOffersThenReturnsOk() throws Exception {
    List<Offer> expectedOffers = Collections.singletonList(Offer.builder()
        .offerId(1L)
        .brandId(1)
        .startDate("2023-01-01T00:00:00Z")
        .endDate("2023-12-31T23:59:59Z")
        .priceListId(1L)
        .productPartnumber("123456789012")
        .priority(1)
        .price(new BigDecimal("10.00"))
        .currencyIso("USD")
        .build());

    when(offerService.findAll()).thenReturn(expectedOffers);

    mockMvc.perform(get("/offer")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].offerId", is(1)))
        .andExpect(jsonPath("$[0].brandId", is(1)))
        .andExpect(jsonPath("$[0].startDate", is("2023-01-01T00:00:00Z")))
        .andExpect(jsonPath("$[0].endDate", is("2023-12-31T23:59:59Z")))
        .andExpect(jsonPath("$[0].priceListId", is(1)))
        .andExpect(jsonPath("$[0].productPartnumber", is("123456789012")))
        .andExpect(jsonPath("$[0].priority", is(1)))
        .andExpect(jsonPath("$[0].price", is(10.00)))
        .andExpect(jsonPath("$[0].currencyIso", is("USD")));
  }

  @Test
  @DisplayName("Success: Given offer ID, when getOfferById is called, then it returns status 200 OK and the offer")
  public void givenOfferIdWhenGetOfferByIdThenReturnsOk() throws Exception {
    Long offerId = 1L;
    Offer expectedOffer = Offer.builder()
        .offerId(offerId)
        .brandId(1)
        .startDate("2023-01-01T00:00:00Z")
        .endDate("2023-12-31T23:59:59Z")
        .priceListId(1L)
        .productPartnumber("123456789012")
        .priority(1)
        .price(new BigDecimal("10.00"))
        .currencyIso("USD")
        .build();

    when(offerService.findById(offerId)).thenReturn(expectedOffer);

    mockMvc.perform(get("/offer/{id}", offerId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.offerId", is(1)))
        .andExpect(jsonPath("$.brandId", is(1)))
        .andExpect(jsonPath("$.startDate", is("2023-01-01T00:00:00Z")))
        .andExpect(jsonPath("$.endDate", is("2023-12-31T23:59:59Z")))
        .andExpect(jsonPath("$.priceListId", is(1)))
        .andExpect(jsonPath("$.productPartnumber", is("123456789012")))
        .andExpect(jsonPath("$.priority", is(1)))
        .andExpect(jsonPath("$.price", is(10.00)))
        .andExpect(jsonPath("$.currencyIso", is("USD")));
  }
}