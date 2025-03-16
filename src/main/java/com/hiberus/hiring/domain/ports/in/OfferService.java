package com.hiberus.hiring.domain.ports.in;

import com.hiberus.hiring.domain.model.Offer;

public interface OfferService {

  void createOffer(Offer offer);

  void deleteAll();

  void deleteById(String offerId);

  void findAll();

  void findById(String offerId);

}
