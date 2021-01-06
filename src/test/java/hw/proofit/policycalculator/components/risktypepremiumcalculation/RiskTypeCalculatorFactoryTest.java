package hw.proofit.policycalculator.components.risktypepremiumcalculation;

import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RiskTypeCalculatorFactoryTest {

    @Autowired
    private RiskTypeCalculatorFactory riskTypeCalculatorFactory;

    @Test
    void factory_returns_correct_component_test() {
        //Act
        RiskTypeCalculator component = riskTypeCalculatorFactory.getComponent(RiskTypeCalculation.MULTIPLY_SUM_BY_COEFFICIENT);

        //Assert
        assertThat(component, instanceOf(MultiplySumByCoefficient.class));
    }
}