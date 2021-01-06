package hw.proofit.policycalculator.components.risktypepremiumcalculation;

import hw.proofit.policycalculator.model.PolicyCalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiplySumByCoefficient implements RiskTypeCalculator {
    @Override
    public List<PolicyCalculation> calculate(List<PolicyCalculation> processedObjects) {
        return processedObjects == null
            ? new ArrayList<>()
            : processedObjects.stream()
                .map(processedObjectEntry -> {
                    BigDecimal initialSum = processedObjectEntry.getSum();
                    BigDecimal coefficient = processedObjectEntry.getCoefficient();
                    return processedObjectEntry.setSum(initialSum.multiply(coefficient));
                })
                .collect(Collectors.toList());
    }
}
