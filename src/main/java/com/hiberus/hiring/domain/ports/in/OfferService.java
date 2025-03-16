package com.hiberus.hiring.domain.ports.in;

import com.hiberus.hiring.domain.model.Offer;
import java.util.List;

public interface OfferService {

  void create(Offer offer);

  void deleteAll();

  void deleteById(Long offerId);

  List<Offer> findAll();

  Offer findById(Long offerId);

}
