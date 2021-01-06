package hw.proofit.policycalculator.components.subobjectcalculation;

import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import org.springframework.stereotype.Component;

@Component
public class SubObjectCalculatorFactory {
    public SubObjectCalculator getComponent(SubObjectCalculation calculationType) {
        switch (calculationType) {
            case SUM_OF_RISK_TYPE_AMOUNTS:
                return new SumOfRiskTypeAmounts();
            default:
                throw new IllegalArgumentException("Processing type " + calculationType + " does not exist");
        }
    }
}
