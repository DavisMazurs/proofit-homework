package hw.proofit.policycalculator.builder;

import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import hw.proofit.policycalculator.model.Policy;

import java.util.Arrays;
import java.util.List;

public class PolicyBuildingObject {
    private Policy source;
    private SubObjectCalculation subObjectCalculation;
    private RiskTypeCalculation riskTypeCalculation;
    private PremiumCalculation premiumCalculation;
    private List<RiskType> riskTypes;

    private PolicyBuildingObject(Policy source,
                                 SubObjectCalculation subObjectCalculation,
                                 RiskTypeCalculation riskTypeCalculation,
                                 PremiumCalculation premiumCalculation,
                                 List<RiskType> riskTypes) {
        this.source = source;
        this.subObjectCalculation = subObjectCalculation;
        this.riskTypeCalculation = riskTypeCalculation;
        this.premiumCalculation = premiumCalculation;
        this.riskTypes = riskTypes;

    }

    static PolicyBuildingObject create(Policy policy,
                                              SubObjectCalculation subObjectCalculation,
                                              RiskTypeCalculation riskTypeCalculation,
                                              PremiumCalculation premiumCalculation,
                                              RiskType... riskTypes) {
        return new PolicyBuildingObject(policy,
                subObjectCalculation,
                riskTypeCalculation,
                premiumCalculation,
                Arrays.asList(riskTypes));
    }

    public Policy getSource() {
        return source;
    }

    public SubObjectCalculation getSubObjectCalculation() {
        return subObjectCalculation;
    }

    public RiskTypeCalculation getRiskTypeCalculation() {
        return riskTypeCalculation;
    }

    public PremiumCalculation getPremiumCalculation() {
        return premiumCalculation;
    }

    public List<RiskType> getRiskTypes() {
        return riskTypes;
    }
}
