package com.hiberus.hiring.infraestructure.persistence;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.repository.OfferRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepositoryImpl extends JpaRepository<OfferEntity, Integer>, OfferRepository {

  @Override
  default Offer create(Offer offer) {
    return null;
  }

  @Override
  default void deleteAll() {
  }

  @Override
  default void deleteById(String offerId) {
  }

  @Override
  default List<Offer> findAllOffers() {
    return new ArrayList<>();
  }

  @Override
  default Offer findOfferById(String offerId) {
    return null;
  }

}
