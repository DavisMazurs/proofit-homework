package hw.proofit.policycalculator.builder;

import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import hw.proofit.policycalculator.model.Policy;

public class PolicyBuildingPatterns {

    private PolicyBuildingPatterns() {
        //static class
    }

    public static PolicyBuildingObject defaultPattern(Policy policy, RiskType... riskTypes) {
        return PolicyBuildingObject.create(
                policy,
                SubObjectCalculation.SUM_OF_RISK_TYPE_AMOUNTS,
                RiskTypeCalculation.MULTIPLY_SUM_BY_COEFFICIENT,
                PremiumCalculation.SUM_ALL_PREMIUMS,
                riskTypes
        );
    }
}
