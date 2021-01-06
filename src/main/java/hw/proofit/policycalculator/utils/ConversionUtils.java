package hw.proofit.policycalculator.utils;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicySubObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ConversionUtils {

    private ConversionUtils() {
        //Utility class
    }

    public static List<PolicySubObject> getAllSubObjects(Policy policy) {
        return policy.getPolicyObjects().stream()
                .filter(policyObject -> policyObject.getSubObjects() != null)
                .flatMap(policyObject -> policyObject.getSubObjects().stream())
                .collect(Collectors.toList());
    }

    public static Map<RiskType, List<BigDecimal>> groupSubObjects(List<PolicySubObject> policySubObjects) {
        return policySubObjects.stream()
                .collect(groupingBy(
                        PolicySubObject::getRiskType,
                        mapping(PolicySubObject::getSumInsured, toList())));
    }
}
