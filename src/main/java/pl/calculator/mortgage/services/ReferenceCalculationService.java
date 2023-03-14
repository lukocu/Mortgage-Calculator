package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.MortgageReference;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.RateAmounts;

public interface ReferenceCalculationService {

    MortgageReference calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageReference calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate);

}
