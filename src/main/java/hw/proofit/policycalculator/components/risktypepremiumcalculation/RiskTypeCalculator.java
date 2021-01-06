package hw.proofit.policycalculator.components.risktypepremiumcalculation;

import hw.proofit.policycalculator.model.PolicyCalculation;

import java.util.List;

public interface RiskTypeCalculator {
    List<PolicyCalculation> calculate(List<PolicyCalculation> processedObjects);
}
