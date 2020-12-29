package hw.proofit.policycalculator.model;

import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

public class PolicySubObject {
    private BigDecimal sumInsured;
    private RiskType riskType;

    private PolicySubObject(BigDecimal sumInsured, RiskType riskType) {
        this.sumInsured = sumInsured;
        this.riskType = riskType;
    }

    public static PolicySubObject create(BigDecimal sumInsured, RiskType riskType) {
        return new PolicySubObject(sumInsured, riskType);
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }
}