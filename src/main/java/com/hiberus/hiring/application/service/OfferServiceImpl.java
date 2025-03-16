package com.hiberus.hiring.application.service;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.in.OfferService;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepository offerRepository;

  @Override
  public void createOffer(Offer offer) {
    this.offerRepository.create(offer);
  }

  @Override
  public void deleteAll() {
    //TODO: Implement this method
  }

  @Override
  public void deleteById(String offerId) {
    //TODO: Implement this method
  }

  @Override
  @Transactional(readOnly = true)
  public void findAll() {
    //TODO: Implement this method
  }

  @Override
  @Transactional(readOnly = true)
  public void findById(String offerId) {
    //TODO: Implement this method
  }
}
