package hw.proofit.policycalculator.components.premiumcalculation;

import hw.proofit.policycalculator.model.PolicyCalculation;

import java.math.BigDecimal;
import java.util.List;

public interface PremiumCalculator {
    BigDecimal calculate(List<PolicyCalculation> riskTypePremiums);
}
