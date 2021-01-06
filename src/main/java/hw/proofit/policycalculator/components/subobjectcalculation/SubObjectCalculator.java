package hw.proofit.policycalculator.components.subobjectcalculation;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.PolicyCalculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SubObjectCalculator {
    List<PolicyCalculation> calculate(Map<RiskType, List<BigDecimal>> groupedSubObjects);
}
