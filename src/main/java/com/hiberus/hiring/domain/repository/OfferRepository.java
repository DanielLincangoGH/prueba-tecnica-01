package com.hiberus.hiring.domain.repository;

import com.hiberus.hiring.domain.model.Offer;
import java.util.List;

public interface OfferRepository {

  Offer create(Offer offer);

  void deleteAll();

  void deleteById(String offerId);

  List<Offer> findAllOffers();

  Offer findOfferById(String offerId);

}
