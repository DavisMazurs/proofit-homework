package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculator;
import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculatorFactory;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculator;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculatorFactory;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculator;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculatorFactory;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import hw.proofit.policycalculator.model.PolicyCalculation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComponentServiceTest {

    @InjectMocks
    private ComponentService componentService;

    @Mock
    private SubObjectCalculatorFactory subObjectFactory;
    @Mock
    private RiskTypeCalculatorFactory riskTypeCalculatorFactory;
    @Mock
    private PremiumCalculatorFactory premiumCalculatorFactory;
    @Mock
    private SubObjectCalculator subObjectCalculator;
    @Mock
    private RiskTypeCalculator riskTypeCalculator;
    @Mock
    private PremiumCalculator premiumCalculator;

    @Test
    void subobject_calculator_test() {
        //Arrange
        Map<RiskType, List<BigDecimal>> input = Collections.singletonMap(RiskType.FIRE, new ArrayList<>());
        List<PolicyCalculation> output = Collections.singletonList(PolicyCalculation.create(RiskType.THEFT, BigDecimal.TEN));
        when(subObjectFactory.getComponent(any())).thenReturn(subObjectCalculator);
        when(subObjectCalculator.calculate(input)).thenReturn(output);

        //Act
        List<PolicyCalculation> result = componentService.subObjectCalculation(input, SubObjectCalculation.SUM_OF_RISK_TYPE_AMOUNTS);

        //Assert
        assertThat(result, is(output));
    }

    @Test
    void risk_type_calculator_test() {
        //Arrange
        List<PolicyCalculation> input = Collections.singletonList(PolicyCalculation.create(RiskType.FIRE, BigDecimal.TEN));
        List<PolicyCalculation> output = Collections.singletonList(PolicyCalculation.create(RiskType.THEFT, BigDecimal.ZERO));
        when(riskTypeCalculatorFactory.getComponent(any())).thenReturn(riskTypeCalculator);
        when(riskTypeCalculator.calculate(input)).thenReturn(output);

        //Act
        List<PolicyCalculation> result = componentService.riskTypeCalculation(input, RiskTypeCalculation.MULTIPLY_SUM_BY_COEFFICIENT);

        //Assert
        assertThat(result, is(output));
    }

    @Test
    void premium_calculator_test() {
        //Arrange
        List<PolicyCalculation> input = Collections.singletonList(PolicyCalculation.create(RiskType.FIRE, BigDecimal.TEN));
        when(premiumCalculatorFactory.getComponent(any())).thenReturn(premiumCalculator);
        when(premiumCalculator.calculate(input)).thenReturn(BigDecimal.valueOf(42));

        //Act
        BigDecimal result = componentService.premiumCalculation(input, PremiumCalculation.SUM_ALL_PREMIUMS);

        //Assert
        assertThat(result, is(BigDecimal.valueOf(42)));
    }
}