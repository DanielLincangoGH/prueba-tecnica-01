package com.hiberus.hiring.infrastructure.adapters.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartNumber;
import com.hiberus.hiring.infrastructure.adapters.mapper.OfferMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfferJpaRepositoryTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private OfferMapper offerMapper;

  @Mock
  private CriteriaBuilder criteriaBuilder;

  @Mock
  private CriteriaDelete<OfferEntity> criteriaDelete;

  @Mock
  private Query query;

  @Mock
  private Root<OfferEntity> root;

  @Mock
  private CriteriaQuery<OfferEntity> criteriaQuery;

  @Mock
  private TypedQuery<OfferEntity> typedQuery;

  @Mock
  private Predicate predicate;

  @Mock
  private Order order;

  @Mock
  private Path<Object> path;

  @InjectMocks
  private OfferJpaRepository offerRepository;

  private Offer offer;
  private OfferEntity offerEntity;

  @BeforeEach
  void setUp() {
    offer = new Offer();
    offerEntity = new OfferEntity();
  }

  @Test
  @DisplayName("Success: Given an offer, when create, then persist the offer entity")
  void givenAnOfferWhenCreateThenPersistTheOfferEntity() {
    when(offerMapper.toOfferEntity(offer)).thenReturn(offerEntity);
    offerRepository.create(offer);
    verify(entityManager, times(1)).persist(offerEntity);
  }

  @Test
  @DisplayName("Success: Given no offer, when delete all, then delete all offer entities")
  void givenNoOfferWhenDeleteAllThenDeleteAllOffers() {
    when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
    when(criteriaBuilder.createCriteriaDelete(OfferEntity.class)).thenReturn(criteriaDelete);
    when(entityManager.createQuery(criteriaDelete)).thenReturn(query);
    when(query.executeUpdate()).thenReturn(1);
    offerRepository.deleteAll();
    verify(entityManager, times(1)).createQuery(criteriaDelete);
  }

  @Test
  @DisplayName("Success: Given offer ID, when deleteById, then delete the offer entity by ID")
  void givenOfferIdWhenDeleteByIdThenDeleteOffer() {
    Long offerId = 1L;
    when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
    when(criteriaBuilder.createCriteriaDelete(OfferEntity.class)).thenReturn(criteriaDelete);
    when(criteriaDelete.from(OfferEntity.class)).thenReturn(root);
    when(entityManager.createQuery(criteriaDelete)).thenReturn(query);
    when(query.executeUpdate()).thenReturn(1);
    offerRepository.deleteById(offerId);
    verify(entityManager, times(1)).createQuery(criteriaDelete);
  }

  @Test
  @DisplayName("Success: Given no offer, when find all, then return all offers")
  void givenNoOfferWhenFindAllThenReturnAllOffers() {
    List<OfferEntity> offerEntities = Collections.singletonList(offerEntity);
    List<Offer> expectedOffers = Collections.singletonList(offer);
    when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
    when(criteriaBuilder.createQuery(OfferEntity.class)).thenReturn(criteriaQuery);
    when(criteriaQuery.from(OfferEntity.class)).thenReturn(root);
    when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(offerEntities);
    when(offerMapper.toDomainList(offerEntities)).thenReturn(expectedOffers);
    List<Offer> actualOffers = offerRepository.findAll();
    assertEquals(expectedOffers, actualOffers);
  }

  @Test
  @DisplayName("Success: Given offer ID, when findById, then return the offer")
  void givenOfferIdWhenFindByIdThenReturnOffer() {
    Long offerId = 1L;
    when(entityManager.find(OfferEntity.class, offerId)).thenReturn(offerEntity);
    when(offerMapper.toDomain(offerEntity)).thenReturn(offer);
    Optional<Offer> actualOffer = offerRepository.findById(offerId);
    assertTrue(actualOffer.isPresent());
    assertEquals(offer, actualOffer.get());
  }

  @Test
  @DisplayName("Success: Given part number and brand ID, when findByPartNumberAndBrand, then return matching offers")
  void givenPartNumberAndBrandIdWhenFindByPartNumberAndBrandThenReturnMatchingOffers() {
    String partNumber = "12345";
    Long brandId = 1L;
    List<OfferEntity> offerEntities = Collections.singletonList(offerEntity);
    List<OfferByPartNumber> expectedOffers = Collections.singletonList(new OfferByPartNumber());

    when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
    when(criteriaBuilder.createQuery(OfferEntity.class)).thenReturn(criteriaQuery);

    when(criteriaQuery.from(OfferEntity.class)).thenReturn(root);

    when(root.get(anyString())).thenReturn(path);
    when(path.get(anyString())).thenReturn(path);

    when(criteriaBuilder.equal(path, brandId)).thenReturn(predicate);
    when(criteriaBuilder.equal(path, partNumber)).thenReturn(predicate);

    when(criteriaBuilder.and(predicate, predicate)).thenReturn(predicate);

    when(criteriaBuilder.asc(path)).thenReturn(order);
    when(criteriaBuilder.desc(path)).thenReturn(order);

    when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
    when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
    when(criteriaQuery.orderBy(order, order)).thenReturn(criteriaQuery);

    when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(offerEntities);
    when(offerMapper.toProductOffersList(offerEntities)).thenReturn(expectedOffers);

    List<OfferByPartNumber> actualOffers = offerRepository.findByPartNumberAndBrand(partNumber,
        brandId);
    assertEquals(expectedOffers, actualOffers);
  }
}
