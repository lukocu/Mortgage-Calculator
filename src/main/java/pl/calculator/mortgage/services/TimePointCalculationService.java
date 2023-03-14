package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.TimePoint;

import java.math.BigDecimal;

public interface TimePointCalculationService {

    TimePoint calculate(final BigDecimal rateNumber, final InputData inputData);

    TimePoint calculate(BigDecimal rateNumber, Rate previousRate);

}
