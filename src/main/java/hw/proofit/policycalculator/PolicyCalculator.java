package hw.proofit.policycalculator;

import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.risktypes.PolicyRiskType;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class PolicyCalculator {

    private final BeanFactory beanFactory;

    @Autowired
    private PolicyCalculator(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String calculate(Policy policy) {
        Validator validator = new Validator();
        String errorMessage = validator.validate(policy);
        if (errorMessage != null) {
            return errorMessage;
        }

        List<PolicySubObject> subObjects = policy.getPolicyObjects()
                .stream()
                .filter(po -> po.getSubObjects() != null)
                .flatMap(po -> po.getSubObjects().stream())
                .collect(Collectors.toList());

        Map<RiskType, BigDecimal> groupedValues = subObjects.stream()
                .collect(groupingBy(PolicySubObject::getRiskType,
                        reducing(BigDecimal.ZERO, PolicySubObject::getSumInsured, BigDecimal::add)));

        Map<RiskType, BigDecimal> riskTypePremiums = groupedValues.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> multiplySumByCoefficient(e.getKey(), e.getValue())));

        return BigDecimalUtils.formatMoney(
                riskTypePremiums.getOrDefault(RiskType.FIRE, BigDecimal.ZERO)
                .add(riskTypePremiums.getOrDefault(RiskType.THEFT, BigDecimal.ZERO)));
    }

    private BigDecimal multiplySumByCoefficient(RiskType type, BigDecimal sum) {
        PolicyRiskType riskType = beanFactory.getBean(type.name().toUpperCase(), PolicyRiskType.class);
        BigDecimal coefficient = riskType.getCoefficient(sum);
        return sum.multiply(coefficient);
    }
}
