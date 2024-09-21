package org.example.calculateneoflex.services.checks;

import java.math.BigInteger;

public interface CheckValiditySalary extends CheckValidity<BigInteger> {
    @Override
    boolean isValid(BigInteger salary);
}
