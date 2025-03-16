package com.hiberus.hiring.infrastructure.adapters.db;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.infrastructure.adapters.mapper.OfferMapper;
import jakarta.persistence.EntityManager;
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
  void deleteAll() {
    // Implement the test for deleteAll method when it is implemented
  }

  @Test
  void deleteById() {
    // Implement the test for deleteById method when it is implemented
  }

  @Test
  void findAll() {
    // Implement the test for findAll method when it is implemented
  }

  @Test
  void findById() {
    // Implement the test for findById method when it is implemented
  }
}
