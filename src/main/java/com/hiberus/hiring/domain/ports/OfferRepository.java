package com.hiberus.hiring.domain.ports;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {

  void create(Offer offer);

  void deleteAll();

  void deleteById(Long offerId);

  List<Offer> findAll();

  Optional<Offer> findById(Long offerId);

  List<OfferByPartNumber> findByPartNumberAndBrand(String partNumber, Long brandId);

}
