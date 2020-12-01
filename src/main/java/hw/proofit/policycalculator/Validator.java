package hw.proofit.policycalculator;

import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyObject;
import hw.proofit.policycalculator.model.PolicySubObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<String> errorMessages;

    public String validate(Policy policy) {
        errorMessages = new ArrayList<>();

        validatePolicyHasAtleastOneObject(policy);
        validateDoesntContainNegativeNumbers(policy);

        return errorMessages.isEmpty() ? null : String.join("; ", errorMessages);
    }

    private void validatePolicyHasAtleastOneObject(Policy policy) {
        if (policy.getPolicyObjects() == null || policy.getPolicyObjects().isEmpty()) {
            errorMessages.add("Error: Policy must contain at least 1 policy object");
        }
    }

    private void validateDoesntContainNegativeNumbers(Policy policy) {
        if (policy.getPolicyObjects() != null) {
            boolean containsNegativeNumbers = policy.getPolicyObjects().stream()
                    .filter(po -> po.getSubObjects() != null)
                    .flatMap(po -> po.getSubObjects().stream())
                    .map(PolicySubObject::getSumInsured)
                    .anyMatch(sum -> BigDecimalUtils.isLessThan(sum, BigDecimal.ZERO));
            if (containsNegativeNumbers) {
                errorMessages.add("Error: Insured sum must not contain negative numbers");
            }
        }
    }
}
