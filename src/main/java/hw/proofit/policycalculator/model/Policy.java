package hw.proofit.policycalculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Policy {
    private List<PolicyObject> policyObjects;

    private Policy(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }

    @JsonCreator
    public static Policy create(@JsonProperty(value = "policyObjects") List<PolicyObject> policyObjects) {
        return new Policy(policyObjects);
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }
}
