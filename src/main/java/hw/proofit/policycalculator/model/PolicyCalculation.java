package hw.proofit.policycalculator.model;

import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

public class PolicyCalculation {
    private RiskType riskType;
    private BigDecimal sum;
    private BigDecimal coefficient;

    private PolicyCalculation(RiskType riskType, BigDecimal sum, BigDecimal coefficient) {
        this.riskType = riskType;
        this.sum = sum;
        this.coefficient = coefficient;
    }

    public static PolicyCalculation create(RiskType riskType, BigDecimal sum) {
        return new PolicyCalculation(riskType, sum, BigDecimal.ZERO);
    }

    public static PolicyCalculation create(RiskType riskType, BigDecimal sum, BigDecimal coefficient) {
        return new PolicyCalculation(riskType, sum, coefficient);
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public PolicyCalculation setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public PolicyCalculation setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
        return this;
    }
}
