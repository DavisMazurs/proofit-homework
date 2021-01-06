package hw.proofit.policycalculator.components.risktypepremiumcalculation;

import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import org.springframework.stereotype.Component;

@Component
public class RiskTypeCalculatorFactory {
    public RiskTypeCalculator getComponent(RiskTypeCalculation calculationType) {
        switch (calculationType) {
            case MULTIPLY_SUM_BY_COEFFICIENT:
                return new MultiplySumByCoefficient();
            default:
                throw new IllegalArgumentException("Calculation type " + calculationType + " does not exist");
        }
    }
}
