package com.hiberus.hiring.application.query.impl;

import com.hiberus.hiring.application.query.OfferQueryService;
import com.hiberus.hiring.domain.exception.OfferAlreadyExistsException;
import com.hiberus.hiring.domain.exception.OfferNotFoundException;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.domain.ports.OfferRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfferQueryServiceImpl implements OfferQueryService {

  private final OfferRepository offerRepository;

  @Override
  public void verifyOfferExists(Long offerId) {
    this.findById(offerId);
  }

  @Override
  public void verifyAlreadyOfferExists(Long offerId) {
    if (offerRepository.findById(offerId).isPresent()) {
      throw new OfferAlreadyExistsException("Offer already exists");
    }
  }

  @Override
  public List<Offer> findAll() {
    return offerRepository.findAll();
  }

  @Override
  public Offer findById(Long offerId) {
    final var offerOptional = offerRepository.findById(offerId);
    if (offerOptional.isEmpty()) {
      throw new OfferNotFoundException("Offer not found");
    }
    return offerOptional.get();
  }

  @Override
  public List<OfferByPartNumber> findByPartNumberAndBrand(String partNumber, Long brandId) {
    return offerRepository.findByPartNumberAndBrand(partNumber, brandId);
  }

}
