package org.example.calculateneoflex.services.checks;

import org.springframework.stereotype.Service;

@Service
public class CheckValidityDaysImpl implements CheckValidityDays {

    @Override
    public boolean isValid(Long days) {
        return days >= 1 && days <= 28;
    }
}
