package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.Rate;

import java.math.BigDecimal;

@FunctionalInterface
public interface Function {

    BigDecimal calculate(Rate rate);
}
