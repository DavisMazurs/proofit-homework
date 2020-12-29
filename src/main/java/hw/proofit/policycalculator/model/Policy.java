package hw.proofit.policycalculator.model;

import java.util.List;

public class Policy {
    private List<PolicyObject> policyObjects;

    private Policy(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }

    public static Policy create(List<PolicyObject> policyObjects) {
        return new Policy(policyObjects);
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }
}
