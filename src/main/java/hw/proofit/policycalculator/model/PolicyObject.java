package hw.proofit.policycalculator.model;

import java.util.List;

public class PolicyObject {
    private List<PolicySubObject> subObjects;

    private PolicyObject(List<PolicySubObject> subObjects) {
        this.subObjects = subObjects;
    }

    public static PolicyObject create(List<PolicySubObject> subObjects) {
        return new PolicyObject(subObjects);
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
