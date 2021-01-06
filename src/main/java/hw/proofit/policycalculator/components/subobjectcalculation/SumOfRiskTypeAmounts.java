package hw.proofit.policycalculator.components.subobjectcalculation;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.PolicyCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SumOfRiskTypeAmounts implements SubObjectCalculator {
    @Override
    public List<PolicyCalculation> calculate(Map<RiskType, List<BigDecimal>> groupedSubObjects) {
        return groupedSubObjects == null
            ? new ArrayList<>()
            : groupedSubObjects.entrySet().stream()
                .map(riskTypeEntry -> PolicyCalculation.create(
                        riskTypeEntry.getKey(),
                        riskTypeEntry.getValue().stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .collect(Collectors.toList());
    }
}
