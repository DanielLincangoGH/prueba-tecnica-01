package com.hiberus.hiring.application.query.impl;

import com.hiberus.hiring.application.query.OfferQueryService;
import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfferQueryServiceImpl implements OfferQueryService {

  private final OfferRepository offerRepository;

  @Override
  public void verifyAlreadyOfferExists(Long offerId) {
    if (offerRepository.findById(offerId).isPresent()) {
      throw new OfferAlreadyExistsException("Offer already exists");
    }
  }

}
