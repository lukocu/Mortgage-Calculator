package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {

    Overpayment calculate(final BigDecimal rateNumber, final InputData inputData);
}
