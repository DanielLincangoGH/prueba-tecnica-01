package com.hiberus.hiring.utils.validators;

import com.hiberus.hiring.domain.model.Offer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;

public class DateRangeValidator implements ConstraintValidator<ValidateDateRange, Offer> {

  @Override
  public boolean isValid(Offer offer, ConstraintValidatorContext constraintValidatorContext) {
    if (offer.getStartDate() == null || offer.getEndDate() == null) {
      return true;
    }
    OffsetDateTime startDate = OffsetDateTime.parse(offer.getStartDate());
    OffsetDateTime endDate = OffsetDateTime.parse(offer.getEndDate());
    return startDate.isBefore(endDate);
  }
}
