package com.hiberus.hiring.infrastructure.adapters.db;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import com.hiberus.hiring.infrastructure.adapters.mapper.OfferMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OfferRepositoryImpl implements OfferRepository {

  private final EntityManager entityManager;
  private final OfferMapper offerMapper;

  @Override
  public void create(Offer offer) {
    var offerEntity = offerMapper.toOfferEntity(offer);
    entityManager.persist(offerEntity);
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
  public List<Offer> findAll() {
    //TODO: Implement this method
    return List.of();
  }

  @Override
  public Offer findById(String offerId) {
    //TODO: Implement this method
    return null;
  }
}
