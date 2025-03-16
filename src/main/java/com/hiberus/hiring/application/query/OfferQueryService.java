package com.hiberus.hiring.application.query;

public interface OfferQueryService {

  void verifyOfferExists(Long offerId);

  void verifyAlreadyOfferExists(Long offerId);
}
