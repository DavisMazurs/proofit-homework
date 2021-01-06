package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculator;
import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculatorFactory;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculator;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculatorFactory;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculator;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculatorFactory;
import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import hw.proofit.policycalculator.model.PolicyCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ComponentService {
    private final SubObjectCalculatorFactory subObjectCalculatorFactory;
    private final RiskTypeCalculatorFactory riskTypeCalculatorFactory;
    private final PremiumCalculatorFactory premiumCalculatorFactory;

    @Autowired
    public ComponentService(SubObjectCalculatorFactory subObjectCalculatorFactory,
                            RiskTypeCalculatorFactory riskTypeCalculatorFactory,
                            PremiumCalculatorFactory premiumCalculatorFactory) {
        this.subObjectCalculatorFactory = subObjectCalculatorFactory;
        this.riskTypeCalculatorFactory = riskTypeCalculatorFactory;
        this.premiumCalculatorFactory = premiumCalculatorFactory;
    }

    public List<PolicyCalculation> subObjectCalculation(Map<RiskType, List<BigDecimal>> source, SubObjectCalculation calculationType) {
        SubObjectCalculator component = subObjectCalculatorFactory.getComponent(calculationType);
        return component.calculate(source);
    }

    public List<PolicyCalculation> riskTypeCalculation(List<PolicyCalculation> source, RiskTypeCalculation calculationType) {
        RiskTypeCalculator component = riskTypeCalculatorFactory.getComponent(calculationType);
        return component.calculate(source);
    }

    public BigDecimal premiumCalculation(List<PolicyCalculation> source, PremiumCalculation calculationType) {
        PremiumCalculator component = premiumCalculatorFactory.getComponent(calculationType);
        return component.calculate(source);
    }
}
