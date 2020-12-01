package hw.proofit.policycalculator.model;

import java.util.List;

public class PolicyObject {
    private String objectName;
    private List<PolicySubObject> subObjects;

    private PolicyObject(String objectName, List<PolicySubObject> subObjects) {
        this.objectName = objectName;
        this.subObjects = subObjects;
    }

    public static PolicyObject create(String objectName, List<PolicySubObject> subObjects) {
        return new PolicyObject(objectName, subObjects);
    }

    public String getObjectName() {
        return objectName;
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
