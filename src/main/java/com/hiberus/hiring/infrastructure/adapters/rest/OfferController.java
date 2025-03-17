package com.hiberus.hiring.infrastructure.adapters.rest;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.domain.model.OfferProduct;
import com.hiberus.hiring.domain.ports.in.OfferService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OfferController {

  private final OfferService offerService;

  @PostMapping(value = "/offer", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewOffer(@RequestBody @Valid Offer offer) {
    log.info("Creating new offer");
    log.debug("DEBUG MODE: Creating new offer: {}", offer);
    this.offerService.create(offer);
    log.info("Creating new offer. SUCCESS");
  }

  @DeleteMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllOffers() {
    log.info("Deleting all offers");
    this.offerService.deleteAll();
    log.info("Deleting all offers. SUCCESS");
  }

  @DeleteMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteOfferById(@PathVariable Long id) {
    log.info("Deleting offer by id");
    log.debug("Deleting offer by id: {}", id);
    this.offerService.deleteById(id);
    log.info("Deleting offer by id. SUCCESS");
  }

  @GetMapping(value = "/offer")
  @ResponseStatus(HttpStatus.OK)
  public List<Offer> getAllOffers() {
    log.info("Getting all offers");
    return this.offerService.findAll();
  }

  @GetMapping(value = "/offer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Offer getOfferById(@PathVariable Long id) {
    log.info("Getting offer by id");
    log.debug("Getting offer by id: {}", id);
    return this.offerService.findById(id);
  }

  @GetMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer")
  @ResponseStatus(HttpStatus.OK)
  public List<OfferByPartNumber> getOfferByPartNumber(OfferProduct offerProduct) {
    log.info("Getting offer by part number");
    log.debug("Getting offer by part number: {}", offerProduct);
    return this.offerService.findByPartNumberAndBrand(offerProduct.getPartnumber(),
        Long.valueOf(offerProduct.getBrandId()));
  }
}
