package org.example.calculateneoflex.services.checks;

public interface CheckValidityDays extends CheckValidity<Long>{
    @Override
    boolean isValid(Long days);
}
