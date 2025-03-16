package com.hiberus.hiring.infrastructure.adapters.db;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.ports.out.OfferRepository;
import com.hiberus.hiring.infrastructure.adapters.mapper.OfferMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OfferJpaRepository implements OfferRepository {

  private final EntityManager entityManager;
  private final OfferMapper offerMapper;

  @Override
  public void create(Offer offer) {
    entityManager.persist(offerMapper.toOfferEntity(offer));
  }

  @Override
  public void deleteAll() {
    final var criteriaBuilder = entityManager.getCriteriaBuilder();
    final var deleteCriteria = criteriaBuilder.createCriteriaDelete(OfferEntity.class);
    entityManager.createQuery(deleteCriteria).executeUpdate();
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
  public Optional<Offer> findById(Long offerId) {
    return Optional.ofNullable(entityManager.find(OfferEntity.class, offerId))
        .map(offerMapper::toDomain);
  }
}
