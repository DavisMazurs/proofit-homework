package hw.proofit.policycalculator.components.premiumcalculation;

import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import org.springframework.stereotype.Component;

@Component
public class PremiumCalculatorFactory {
    public PremiumCalculator getComponent(PremiumCalculation calculationType) {
        switch (calculationType) {
            case SUM_ALL_PREMIUMS:
                return new SumAllRiskTypePremiums();
            default:
                throw new IllegalArgumentException("Calculation type " + calculationType + " does not exist");
        }
    }
}
