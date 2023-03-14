package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.Summary;

import java.util.List;

public interface SummaryService {

    Summary calculateSummary(List<Rate> rates);
}
