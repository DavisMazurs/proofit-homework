package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.BigDecimalUtils;
import hw.proofit.policycalculator.PolicyValidationException;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicySubObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {
    private final PolicyService policyService;

    private List<String> errorMessages = new ArrayList<>();

    @Autowired
    public ValidationService(PolicyService policyService) {
        this.policyService = policyService;
    }

    public void validate(Policy policy) throws PolicyValidationException {
        errorMessages.clear();

        validatePolicyHasAtleastOneObject(policy);
        validateDoesntContainNegativeNumbers(policy);

        if (!errorMessages.isEmpty()) {
            throw new PolicyValidationException(String.join("; ", errorMessages));
        }
    }

    private void validatePolicyHasAtleastOneObject(Policy policy) {
        if (policy.getPolicyObjects() == null || policy.getPolicyObjects().isEmpty()) {
            errorMessages.add("Error: Policy must contain at least 1 policy object");
        }
    }

    private void validateDoesntContainNegativeNumbers(Policy policy) {
        if (policy.getPolicyObjects() != null) {
            boolean containsNegativeNumbers = policyService.getAllSubObjects(policy).stream()
                    .map(PolicySubObject::getSumInsured)
                    .anyMatch(totalSum -> BigDecimalUtils.isLessThan(totalSum, BigDecimal.ZERO));
            if (containsNegativeNumbers) {
                errorMessages.add("Error: Insured sum must not contain negative numbers");
            }
        }
    }
}