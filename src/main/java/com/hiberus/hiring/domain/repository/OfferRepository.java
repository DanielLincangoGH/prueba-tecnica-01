package com.hiberus.hiring.domain.repository;

import com.hiberus.hiring.domain.model.Offer;
import java.util.List;

public interface OfferRepository {

  void create(Offer offer);

  void deleteAll();

  void deleteById(String offerId);

  List<Offer> findAll();

  Offer findById(String offerId);

}
