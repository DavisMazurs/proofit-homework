package hw.proofit.policycalculator.model;

import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

public class PolicyCalculation {
    private RiskType riskType;
    private BigDecimal sum;

    private PolicyCalculation(RiskType riskType, BigDecimal sum) {
        this.riskType = riskType;
        this.sum = sum;
    }

    public static PolicyCalculation create(RiskType riskType, BigDecimal sum) {
        return new PolicyCalculation(riskType, sum);
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
