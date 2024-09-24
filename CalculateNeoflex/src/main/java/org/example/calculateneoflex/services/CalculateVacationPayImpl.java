package org.example.calculateneoflex.services;

import lombok.RequiredArgsConstructor;
import org.example.calculateneoflex.dao.VacationPayDAO;
import org.example.calculateneoflex.services.checks.CheckValidityDays;
import org.example.calculateneoflex.services.checks.CheckValiditySalary;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculateVacationPayImpl implements CalculateVacationPay<VacationPayDAO>{

    private final CheckValidityDays checkValidityDays;
    private final CheckValiditySalary checkValiditySalary;

    @Override
    public VacationPayDAO calculate(BigInteger salary, byte days) throws IllegalArgumentException{
        if (!checkValiditySalary.isValid(salary))throw new IllegalArgumentException("Средняя зарплата должна быть больше 0");
        if(!checkValidityDays.isValid((long) days)) throw new IllegalArgumentException("Количество отпускных дней должно быть не менее 1 и не более 28");
        return new VacationPayDAO(salary.multiply(BigInteger.valueOf(days)));
    }

    @Override
    public VacationPayDAO calculate(BigInteger salary,byte days ,LocalDate dateVacationStart, LocalDate dateVacationEnd) throws IllegalArgumentException{
        long differenceDays = dateVacationEnd.getDayOfYear()-dateVacationStart.getDayOfYear();
        long durationVacation = differenceDays<0?365+differenceDays:differenceDays;
        if (days!=durationVacation)throw new IllegalArgumentException("Количество дней взятых в отпуск не совпадает с датами");
        if (!checkValiditySalary.isValid(salary))throw new IllegalArgumentException("Средняя зарплата должна быть больше 0");
        if(!checkValidityDays.isValid(durationVacation)) throw new IllegalArgumentException("Количество отпускных дней должно быть не менее 1 и не более 28");
        return new VacationPayDAO(salary.multiply(BigInteger.valueOf(durationVacation)));
    }
}
