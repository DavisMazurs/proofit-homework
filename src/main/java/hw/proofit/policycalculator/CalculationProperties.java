package hw.proofit.policycalculator;

import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

/**
 * This is where risk types are configured
 * The system is made so it's easy to add new risk types
 * All you have to do is add enum value, and create a new case in the getCoefficient method
 * in which you can specify the rules for applying a multiplication coefficient
 */

public class CalculationProperties {

    private CalculationProperties() {
        //static class
    }

    public static BigDecimal calculateRiskTypePremium(RiskType riskType, BigDecimal sum) {
        return sum.multiply(getCoefficient(riskType, sum));
    }

    private static BigDecimal getCoefficient(RiskType riskType, BigDecimal sum) {
        switch (riskType) {
            case FIRE:
                return BigDecimalUtils.isGreaterThan(sum, BigDecimal.valueOf(100)) ? BigDecimal.valueOf(0.024) : BigDecimal.valueOf(0.014);
            case THEFT:
                return BigDecimalUtils.isGreaterThanOrEqual(sum, BigDecimal.valueOf(15)) ? BigDecimal.valueOf(0.05) : BigDecimal.valueOf(0.11);
            default:
                return BigDecimal.ZERO;
        }
    }
}
