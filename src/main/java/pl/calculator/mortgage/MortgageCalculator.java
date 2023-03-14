package pl.calculator.mortgage;

import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.MortgageType;
import pl.calculator.mortgage.model.Overpayment;
import pl.calculator.mortgage.services.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class MortgageCalculator {

    public static void main(String[] args) {

        InputData inputData;
        try {
          inputData=  new InputDataService().read();
            CalculatorCreator.getInstance().calculate(inputData);

        } catch (Exception e) {
           System.err.println("Error loading input data, interrupting. Error"+ e.getMessage());

        }


    }

    static class CalculatorCreator{
        private static MortgageCalculationService instance;

        private CalculatorCreator() {
        }

        public static MortgageCalculationService getInstance(){
            if(Objects.isNull(instance)){
                instance = new MortgageCalculationServiceImpl(
                        new RateCalculationServiceImpl(
                                new TimePointCalculationServiceImpl(),
                                new OverpaymentCalculationServiceImpl(),
                                new AmountsCalculationServiceImpl(
                                        new ConstantAmountsCalculationServiceImpl(),
                                        new DecreasingAmountsCalculationServiceImpl()
                                ),
                                new ResidualCalculationServiceImpl(),
                                new ReferenceCalculationServiceImpl()
                        ),
                        new PrintingServiceImpl(),
                        SummaryServiceFactory.create()
                );
            }
            return instance;
        }
    }
}
