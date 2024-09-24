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

    @GetMapping("/salary/{salary}/vacation")
    public VacationPayDAO calculateVacationPay(@PathVariable BigInteger salary, byte days, @RequestParam(value = "start",required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateVacationStart, @RequestParam(value = "finish",required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateVacationEnd ) {
        if(dateVacationStart != null && dateVacationEnd != null) {
            return calculateVacationPay.calculate(salary,days,dateVacationStart,dateVacationEnd);
        }
        return calculateVacationPay.calculate(salary,days);
    }
}
