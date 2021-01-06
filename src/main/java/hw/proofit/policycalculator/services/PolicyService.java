package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.builder.PolicyBuildingObject;
import hw.proofit.policycalculator.model.PolicyCalculation;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.risktypes.PolicyRiskType;
import hw.proofit.policycalculator.utils.ConversionUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class PolicyService {
    private final BeanFactory beanFactory;
    private final ComponentService componentService;

    @Autowired
    public PolicyService(BeanFactory beanFactory,
                         ComponentService componentService) {
        this.beanFactory = beanFactory;
        this.componentService = componentService;
    }

    public BigDecimal calculatePremium(PolicyBuildingObject builder) {
        List<PolicySubObject> subObjects = ConversionUtils.getAllSubObjects(builder.getSource());
        Map<RiskType, List<BigDecimal>> groupedSubObjects = ConversionUtils.groupSubObjects(subObjects);

        List<PolicyCalculation> calculatedSubObjects = componentService.subObjectCalculation(groupedSubObjects, builder.getSubObjectCalculation());
        List<PolicyCalculation> processedSubObjects = processSubObjects(calculatedSubObjects, builder.getRiskTypes());
        List<PolicyCalculation> riskTypePremiums = componentService.riskTypeCalculation(processedSubObjects, builder.getRiskTypeCalculation());
        return componentService.premiumCalculation(riskTypePremiums, builder.getPremiumCalculation());
    }

    private List<PolicyCalculation> processSubObjects(List<PolicyCalculation> subObjects, List<RiskType> riskTypes) {
        return subObjects.stream()
                .filter(subObject -> riskTypes.isEmpty() || riskTypes.contains(subObject.getRiskType()))
                .map(this::getCoefficient)
                .collect(toList());
    }

    private PolicyCalculation getCoefficient(PolicyCalculation policyCalculation) {
        PolicyRiskType riskType = beanFactory.getBean(policyCalculation.getRiskType().name().toUpperCase(), PolicyRiskType.class);
        return policyCalculation.setCoefficient(riskType.getCoefficient(policyCalculation.getSum()));
    }
}
