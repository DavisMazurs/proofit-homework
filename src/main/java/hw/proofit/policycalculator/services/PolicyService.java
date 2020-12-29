package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyCalculation;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.risktypes.PolicyRiskType;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Service
public class PolicyService {
    private final BeanFactory beanFactory;

    @Autowired
    public PolicyService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public List<PolicySubObject> getAllSubObjects(Policy policy) {
        return policy.getPolicyObjects().stream()
                .filter(policyObject -> policyObject.getSubObjects() != null)
                .flatMap(policyObject -> policyObject.getSubObjects().stream())
                .collect(Collectors.toList());
    }

    public List<PolicyCalculation> getEachRiskTypePremium(List<PolicySubObject> policySubObjects) {
        return getEachRiskTypeSum(policySubObjects).entrySet().stream()
                .map(riskTypeEntry -> PolicyCalculation.create(
                                riskTypeEntry.getKey(),
                                getPremium(riskTypeEntry.getKey(), riskTypeEntry.getValue())
                        ))
                .collect(Collectors.toList());
    }

    private Map<RiskType, BigDecimal> getEachRiskTypeSum(List<PolicySubObject> policySubObjects) {
        return policySubObjects.stream()
                .collect(groupingBy(
                        PolicySubObject::getRiskType,
                        reducing(BigDecimal.ZERO, PolicySubObject::getSumInsured, BigDecimal::add)));
    }

    private BigDecimal getPremium(RiskType type, BigDecimal sum) {
        PolicyRiskType riskType = beanFactory.getBean(type.name().toUpperCase(), PolicyRiskType.class);
        return sum.multiply(riskType.getCoefficient(sum));
    }
}
