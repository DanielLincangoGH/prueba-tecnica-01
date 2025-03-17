package com.hiberus.hiring.application.command.impl;

import com.hiberus.hiring.application.command.OfferCommandService;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferCommandServiceImpl implements OfferCommandService {

  private final OfferRepository offerRepository;

  @Override
  public void create(Offer offer) {
    this.offerRepository.create(offer);
  }

  @Override
  public void deleteAll() {
    this.offerRepository.deleteAll();
  }

  @Override
  public void deleteById(Long offerId) {
    this.offerRepository.deleteById(offerId);
  }

}
