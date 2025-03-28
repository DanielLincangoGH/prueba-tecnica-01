package com.hiberus.hiring.infrastructure.adapters.db;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.domain.ports.OfferRepository;
import com.hiberus.hiring.infrastructure.adapters.mapper.OfferMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
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
  public void deleteById(Long offerId) {
    final var criteriaBuilder = entityManager.getCriteriaBuilder();
    final var deleteCriteria = criteriaBuilder.createCriteriaDelete(OfferEntity.class);
    Root<OfferEntity> root = deleteCriteria.from(OfferEntity.class);
    deleteCriteria.where(criteriaBuilder.equal(root.get("id"), offerId));
    entityManager.createQuery(deleteCriteria).executeUpdate();
  }

  @Override
  public List<Offer> findAll() {
    var criteriaBuilder = entityManager.getCriteriaBuilder();
    final var query = criteriaBuilder.createQuery(OfferEntity.class);
    Root<OfferEntity> root = query.from(OfferEntity.class);
    query.select(root);
    return offerMapper.toDomainList(entityManager.createQuery(query).getResultList());
  }

  @Override
  public Optional<Offer> findById(Long offerId) {
    return Optional.ofNullable(entityManager.find(OfferEntity.class, offerId))
        .map(offerMapper::toDomain);
  }

  @Override
  public List<OfferByPartNumber> findByPartNumberAndBrand(String partNumber, Long brandId) {
    final var criteriaBuilder = entityManager.getCriteriaBuilder();
    final var query = criteriaBuilder.createQuery(OfferEntity.class);
    final var offer = query.from(OfferEntity.class);
    final var brandPredicate = criteriaBuilder.equal(offer.get("brand").get("id"), brandId);
    final var partNumberPredicate = criteriaBuilder.equal(offer.get("partnumber"), partNumber);
    query.select(offer)
        .where(criteriaBuilder.and(brandPredicate, partNumberPredicate))
        .orderBy(criteriaBuilder.asc(offer.get("startDate")),
            criteriaBuilder.desc(offer.get("priority")));
    return offerMapper.toProductOffersList(entityManager.createQuery(query).getResultList());
  }

}
