package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Overpayment;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.RateAmounts;

public interface DecreasingAmountsCalculationService {

    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
