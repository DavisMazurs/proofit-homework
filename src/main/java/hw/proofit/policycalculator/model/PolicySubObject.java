package hw.proofit.policycalculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hw.proofit.policycalculator.enums.RiskType;

import java.math.BigDecimal;

public class PolicySubObject {
    private BigDecimal sumInsured;
    private RiskType riskType;

    private PolicySubObject(BigDecimal sumInsured, RiskType riskType) {
        this.sumInsured = sumInsured;
        this.riskType = riskType;
    }

    @JsonCreator
    public static PolicySubObject create(@JsonProperty(value = "sumInsured") BigDecimal sumInsured,
                                         @JsonProperty(value = "riskType") RiskType riskType) {
        return new PolicySubObject(sumInsured, riskType);
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }
}