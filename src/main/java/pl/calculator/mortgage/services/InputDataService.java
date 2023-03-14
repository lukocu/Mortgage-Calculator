package pl.calculator.mortgage.services;

import lombok.Builder;
import pl.calculator.mortgage.model.InputData;
import pl.calculator.mortgage.model.MortgageType;
import pl.calculator.mortgage.model.Overpayment;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputDataService {

    private static final Path FILE_LOCATION = Paths.get("src/main/resources/inputData.csv");

    public InputData read() throws IOException{
      var content= Files.readString(FILE_LOCATION)
                .lines()
                .collect(Collectors.groupingBy(line -> line.split(";")[0]));

                validate(content);

              var inputData=  content.entrySet().stream()
                        .collect(Collectors
                                .toMap(Map.Entry::getKey, entry -> entry.getValue().get(0).split(";")[1]));

        return InputData.builder()
                .repaymentStartDate(
                        Optional.ofNullable(inputData.get("repaymentStartDate")).map(LocalDate::parse).orElseThrow())
                .wiborPercent(
                        Optional.ofNullable(inputData.get("wibor")).map(BigDecimal::new).orElseThrow())
                .amount(
                        Optional.ofNullable(inputData.get("amount")).map(BigDecimal::new).orElseThrow())
                .monthsDuration(
                        Optional.ofNullable(inputData.get("monthsDuration")).map(BigDecimal::new).orElseThrow())
                .rateType(
                        Optional.ofNullable(inputData.get("rateType")).map(MortgageType::valueOf).orElseThrow())
                .marginPercent(
                        Optional.ofNullable(inputData.get("margin")).map(BigDecimal::new).orElseThrow())
                .overpaymentProvisionPercent(
                        Optional.ofNullable(inputData.get("overpaymentProvision")).map(BigDecimal::new).orElseThrow())
                .overpaymentProvisionMonths(
                        Optional.ofNullable(inputData.get("overpaymentProvisionMonths")).map(BigDecimal::new).orElseThrow())
                .overpaymentStartMonth(
                        Optional.ofNullable(inputData.get("overpaymentStartMonth")).map(BigDecimal::new).orElseThrow())
                .overpaymentSchema(
                        Optional.ofNullable(inputData.get("overpaymentSchema")).map(this::calculateSchema).orElseThrow())
                .overpaymentReduceWay(
                        Optional.ofNullable(inputData.get("overpaymentReduceWay")).orElseThrow())
                .mortgagePrintPayoffsSchedule(
                        Optional.ofNullable(inputData.get("mortgagePrintPayoffsSchedule")).map(Boolean::parseBoolean).orElseThrow())
                .mortgageRateNumberToPrint(
                        Optional.ofNullable(inputData.get("mortgageRateNumberToPrint")).map(Integer::parseInt).orElseThrow())
                .build();

    }

    private Map<Integer, BigDecimal> calculateSchema(String schema) {
        return Stream.of(schema.split(","))
                .map(entry -> Map.entry(entry.split(":")[0], entry.split(":")[1]))
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.getKey()),
                        entry -> new BigDecimal(entry.getValue()),
                        (v1, v2) -> v2,
                        TreeMap::new
                ));
    }

    private static void validate(final Map<String, List<String>> content){
        if(content.values().stream().anyMatch(values -> values.size() != 1))
            throw new IllegalArgumentException("Configuration mismatch");
    }
}
