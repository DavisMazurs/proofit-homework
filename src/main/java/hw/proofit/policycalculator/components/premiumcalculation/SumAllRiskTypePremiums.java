package hw.proofit.policycalculator.components.premiumcalculation;

import hw.proofit.policycalculator.model.PolicyCalculation;

import java.math.BigDecimal;
import java.util.List;

public class SumAllRiskTypePremiums implements PremiumCalculator {
    @Override
    public BigDecimal calculate(List<PolicyCalculation> riskTypePremiums) {
        return riskTypePremiums == null
            ? BigDecimal.ZERO
            : riskTypePremiums.stream()
                .map(PolicyCalculation::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
