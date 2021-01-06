package hw.proofit.policycalculator.components.subobjectcalculation;

import hw.proofit.policycalculator.components.premiumcalculation.SumAllRiskTypePremiums;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubObjectCalculatorFactoryTest {

    @Autowired
    SubObjectCalculatorFactory subObjectCalculatorFactory;

    @Test
    void factory_returns_correct_component_test() {
        //Act
        SubObjectCalculator component = subObjectCalculatorFactory.getComponent(SubObjectCalculation.SUM_OF_RISK_TYPE_AMOUNTS);

        //Assert
        assertThat(component, instanceOf(SumOfRiskTypeAmounts.class));
    }
}