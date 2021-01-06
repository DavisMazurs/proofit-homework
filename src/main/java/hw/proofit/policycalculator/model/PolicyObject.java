package hw.proofit.policycalculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PolicyObject {
    private List<PolicySubObject> subObjects;

    private PolicyObject(List<PolicySubObject> subObjects) {
        this.subObjects = subObjects;
    }

    @JsonCreator
    public static PolicyObject create(@JsonProperty(value = "subObjects") List<PolicySubObject> subObjects) {
        return new PolicyObject(subObjects);
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
