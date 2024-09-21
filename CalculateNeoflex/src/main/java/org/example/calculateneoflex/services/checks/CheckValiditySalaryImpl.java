package org.example.calculateneoflex.services.checks;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class CheckValiditySalaryImpl implements CheckValiditySalary {
    @Override
    public boolean isValid(BigInteger salary) {
        return salary.compareTo(BigInteger.ZERO) > 0;
    }
}
