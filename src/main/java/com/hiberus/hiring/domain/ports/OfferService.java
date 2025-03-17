package com.hiberus.hiring.domain.ports;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface OfferService {

  void create(Offer offer);

  void deleteAll();

  void deleteById(Long offerId);

  List<Offer> findAll();

  Offer findById(Long offerId);

  List<OfferByPartNumber> findByPartNumberAndBrand(String partNumber, Long brandId);
}
