package com.hiberus.hiring.infrastructure.adapters.rest;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.domain.ports.in.OfferService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OfferController {

  private final OfferService offerService;

  @PostMapping(value = "/offer", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewOffer(@RequestBody @Valid Offer offer) {
    this.offerService.create(offer);
  }

  @DeleteMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllOffers() {
    this.offerService.deleteAll();
  }

  @DeleteMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteOfferById(@RequestParam Long id) {
    //TODO implement it!.
  }

  @GetMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public List<Offer> getAllOffers() {
    //TODO implement it!.
    return new ArrayList<>();

  }

  @GetMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Offer getOfferById(Long offerId) {
    //TODO implement it!.
    return new Offer();
  }

  @GetMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer")
  @ResponseStatus(HttpStatus.OK)
  public List<OfferByPartNumber> getOfferByPartNumber(Integer brandId, String partnumber) {
    //TODO implement it!.
    return new ArrayList<>();
  }
}
