package com.hiberus.hiring.application.query;

import com.hiberus.hiring.domain.model.Offer;
import java.util.List;

public interface OfferQueryService {

  void verifyOfferExists(Long offerId);

  void verifyAlreadyOfferExists(Long offerId);

  List<Offer> findAll();
}
