package pl.calculator.mortgage.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.Summary;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final RateCalculationService rateCalculationService;

    private final PrintingService printingService;

    private final SummaryService summaryService;

    @Override
    public void calculate(InputData inputData) {
        printingService.printIntroInformation(inputData);

        List<Rate> rates = rateCalculationService.calculate(inputData);
        rates.forEach(element -> log.debug("Rate: [{}]", element));
        Summary summary = summaryService.calculateSummary(rates);

        printingService.printSummary(summary);
        printingService.printSchedule(rates, inputData);
    }

}
