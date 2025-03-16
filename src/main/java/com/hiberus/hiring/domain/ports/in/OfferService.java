package com.hiberus.hiring.domain.ports.in;

import com.hiberus.hiring.domain.model.Offer;

public interface OfferService {

  void create(Offer offer);

  void deleteAll();

  void deleteById(Long offerId);

  void findAll();

  void findById(Long offerId);

}
