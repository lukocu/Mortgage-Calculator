package pl.calculator.mortgage.services;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.Rate;
import pl.calculator.mortgage.model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointCalculationServiceImpl implements TimePointCalculationService {

    public TimePoint calculate(final BigDecimal rateNumber, final InputData inputData) {
        if (!BigDecimal.ONE.equals(rateNumber)) {
            throw new RuntimeException("This method only accepts rateNumber equal to ONE");
        }
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);
        LocalDate date = inputData.getRepaymentStartDate();
        return new TimePoint(year, month, date);
    }

    public TimePoint calculate(BigDecimal rateNumber, Rate previousRate) {
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);
        LocalDate date = previousRate.getTimePoint().getDate().plus(1, ChronoUnit.MONTHS);
        return new TimePoint(year, month, date);
    }

    private BigDecimal calculateYear(final BigDecimal rateNumber) {
        return rateNumber.divide(AmountsCalculationService.YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private BigDecimal calculateMonth(final BigDecimal rateNumber) {
        return BigDecimal.ZERO.equals(rateNumber.remainder(AmountsCalculationService.YEAR))
            ? AmountsCalculationService.YEAR
            : rateNumber.remainder(AmountsCalculationService.YEAR);
    }

}
