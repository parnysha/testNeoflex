package org.example.calculateneoflex.serviceTests;


import org.example.calculateneoflex.dao.VacationPayDAO;
import org.example.calculateneoflex.services.CalculateVacationPay;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;

@SpringBootTest
public class CalculatevacationPayTest {

    @Autowired
    CalculateVacationPay<VacationPayDAO> calculateVacationPay;

    @Test
    public void testCalculateBySalaryAndDays() {
        VacationPayDAO expectedResult = new VacationPayDAO(BigInteger.valueOf(100));
        VacationPayDAO result = calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 10);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateByNotValidSalaryAndDays() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(0),(byte) 10));
    }

    @Test
    public void testCalculateBySalaryAndNotValidDays() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 29));
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 0));
    }

    @Test
    public void testCalculateBySalaryWithDatesInOneYears() {
        VacationPayDAO expectedResult = new VacationPayDAO(BigInteger.valueOf(100));
        VacationPayDAO result = calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 10,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 11));
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateByNotValidSalaryDatesInOneYears() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(0),(byte) 10,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 11)));
    }

    @Test
    public void testCalculateBySalaryAndNotValidDaysInOneYears() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 1,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 1)));
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 30,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 30)));
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 20,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 11)));
    }

    @Test
    public void testCalculateBySalaryWithDatesInDifferentYears() {
        VacationPayDAO expectedResult = new VacationPayDAO(BigInteger.valueOf(100));
        VacationPayDAO result = calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 10,LocalDate.of(2001, 12, 22),LocalDate.of(2002, 1, 1));
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateByNotValidSalaryDatesInDifferentYears() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(0),(byte) 10,LocalDate.of(2001, 12, 22),LocalDate.of(2002, 1, 1)));
    }

    @Test
    public void testCalculateBySalaryAndNotValidDaysInDifferentYears() {
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 1,LocalDate.of(2001, 1, 1),LocalDate.of(2001, 1, 1)));
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 36,LocalDate.of(2001, 12, 10),LocalDate.of(2002, 1, 15)));
        assertThrows(IllegalArgumentException.class, () -> calculateVacationPay.calculate(BigInteger.valueOf(10),(byte) 20,LocalDate.of(2001, 12, 22),LocalDate.of(2002, 1, 1)));
    }

}
