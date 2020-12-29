package hw.proofit.policycalculator;

import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyCalculation;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.services.PolicyService;
import hw.proofit.policycalculator.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PolicyCalculator {
    private final ValidationService validationService;
    private final PolicyService policyService;

    @Autowired
    private PolicyCalculator(ValidationService validationService,
                             PolicyService policyService) {
        this.validationService = validationService;
        this.policyService = policyService;
    }

    public String calculate(Policy policy) {
        try {
            validationService.validate(policy);

            List<PolicySubObject> subObjects = policyService.getAllSubObjects(policy);
            List<PolicyCalculation> policyPremiums = policyService.getEachRiskTypePremium(subObjects);
            BigDecimal premium = policyPremiums.stream()
                    .map(PolicyCalculation::getSum)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return BigDecimalUtils.formatMoney(premium);
        } catch (PolicyValidationException e) {
            return e.getMessage();
        }
    }
}
