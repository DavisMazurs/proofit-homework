package hw.proofit.policycalculator.model;

import hw.proofit.policycalculator.enums.PolicyStatus;

import java.util.List;

public class Policy {
    private String policyNumber;
    private PolicyStatus policyStatus;
    private List<PolicyObject> policyObjects;

    private Policy(String policyNumber, PolicyStatus policyStatus, List<PolicyObject> policyObjects) {
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus;
        this.policyObjects = policyObjects;
    }

    public static Policy create(String policyNumber, PolicyStatus policyStatus, List<PolicyObject> policyObjects) {
        return new Policy(policyNumber, policyStatus, policyObjects);
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }
}
