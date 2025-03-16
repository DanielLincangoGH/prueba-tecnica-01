package com.hiberus.hiring.application.command;

import com.hiberus.hiring.domain.model.Offer;

public interface OfferCommandService {

  void create(Offer offer);

  void deleteAll();
}
