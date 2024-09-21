package org.example.calculateneoflex.services;

import java.math.BigInteger;
import java.time.LocalDate;

public interface CalculateVacationPay<T> {
    T calculate(BigInteger salary, byte days);
    T calculate(BigInteger salary, LocalDate dateVacationStart, LocalDate dateVacationEnd);
}
