package hw.proofit.policycalculator.model;

import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

public class PolicySubObject {
    private String subObjectName;
    private BigDecimal sumInsured;
    private RiskType riskType;

    private PolicySubObject(String subObjectName, BigDecimal sumInsured, RiskType riskType) {
        this.subObjectName = subObjectName;
        this.sumInsured = sumInsured;
        this.riskType = riskType;
    }

    public static PolicySubObject create(String subObjectName, BigDecimal sumInsured, RiskType riskType) {
        return new PolicySubObject(subObjectName, sumInsured, riskType);
    }

    public String getSubObjectName() {
        return subObjectName;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }
}
