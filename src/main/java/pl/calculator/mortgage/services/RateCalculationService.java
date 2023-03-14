package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Rate;

import java.util.List;

public interface RateCalculationService {

    List<Rate> calculate(InputData inputData);
}
