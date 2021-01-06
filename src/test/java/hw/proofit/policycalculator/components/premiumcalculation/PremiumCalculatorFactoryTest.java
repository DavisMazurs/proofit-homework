package hw.proofit.policycalculator.components.premiumcalculation;

import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PremiumCalculatorFactoryTest {

    @Autowired
    private PremiumCalculatorFactory premiumCalculatorFactory;

    @Test
    void factory_returns_correct_component_test() {
        //Act
        PremiumCalculator component = premiumCalculatorFactory.getComponent(PremiumCalculation.SUM_ALL_PREMIUMS);

        //Assert
        assertThat(component, instanceOf(SumAllRiskTypePremiums.class));
    }
}