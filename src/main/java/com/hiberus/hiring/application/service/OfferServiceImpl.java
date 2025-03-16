package com.hiberus.hiring.application.service;

import com.hiberus.hiring.application.command.OfferCommandService;
import com.hiberus.hiring.application.query.BrandQueryService;
import com.hiberus.hiring.application.query.OfferQueryService;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.in.OfferService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final BrandQueryService brandQueryService;
  private final OfferCommandService offerCommandService;
  private final OfferQueryService offerQueryService;

  @Override
  public void create(Offer offer) {
    this.brandQueryService.verifyBrand(Long.valueOf(offer.getBrandId()));
    this.offerQueryService.verifyAlreadyOfferExists(offer.getOfferId());
    this.offerCommandService.create(offer);
  }

  @Override
  public void deleteAll() {
    this.offerCommandService.deleteAll();
  }

  @Override
  public void deleteById(Long offerId) {
    this.offerQueryService.verifyOfferExists(offerId);
    this.offerCommandService.deleteById(offerId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Offer> findAll() {
    return this.offerQueryService.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Offer findById(Long offerId) {
    return Offer.builder().build();
  }
}
