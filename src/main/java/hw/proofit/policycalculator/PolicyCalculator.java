package hw.proofit.policycalculator;

import hw.proofit.policycalculator.builder.PolicyBuildingPatterns;
import hw.proofit.policycalculator.utils.BigDecimalUtils;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.builder.PolicyBuildingObject;
import hw.proofit.policycalculator.services.PolicyService;
import hw.proofit.policycalculator.services.PolicyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PolicyCalculator {
    private final PolicyValidationService policyValidationService;
    private final PolicyService policyService;

    @Autowired
    private PolicyCalculator(PolicyValidationService policyValidationService,
                             PolicyService policyService) {
        this.policyValidationService = policyValidationService;
        this.policyService = policyService;
    }

    public String calculate(Policy policy) throws PolicyValidationException {
        policyValidationService.validate(policy);

        PolicyBuildingObject policyBuildingObject = PolicyBuildingPatterns.defaultPattern(policy);
        BigDecimal premium = policyService.calculatePremium(policyBuildingObject);
        return BigDecimalUtils.formatMoney(premium);
    }
}
