package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.MortgageResidual;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.RateAmounts;

public interface ResidualCalculationService {

    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageResidual calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate);

}
