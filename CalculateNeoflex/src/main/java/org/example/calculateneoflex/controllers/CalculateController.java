package org.example.calculateneoflex.controllers;


import lombok.RequiredArgsConstructor;
import org.example.calculateneoflex.dao.VacationPayDAO;
import org.example.calculateneoflex.services.CalculateVacationPay;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/calculate")
public class CalculateController {
    private final CalculateVacationPay<VacationPayDAO> calculateVacationPay;

    @GetMapping("/salary/{salary}/vacation/{days}")
    public VacationPayDAO calculateVacationPay(@PathVariable BigInteger salary, @PathVariable byte days) {
        return calculateVacationPay.calculate(salary,days);
    }
    @GetMapping("/salary/{salary}/first-date/{dateVacationStart}/second-date/{dateVacationEnd}")
    public VacationPayDAO calculateVacationPay(@PathVariable BigInteger salary, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateVacationStart,@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateVacationEnd) {
        return calculateVacationPay.calculate(salary,dateVacationStart,dateVacationEnd);
    }
}
