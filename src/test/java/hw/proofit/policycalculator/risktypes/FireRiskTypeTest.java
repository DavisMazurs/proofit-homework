package hw.proofit.policycalculator.risktypes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class FireRiskTypeTest {

    @Autowired
    private FireRiskType fireRiskType;

    @Test
    void fire_type_coefficient_test() {
        //Arrange
        BigDecimal valueForDefault = BigDecimal.valueOf(90);
        BigDecimal valueForAlternate = BigDecimal.valueOf(120);

        //Act
        BigDecimal defaultValue = fireRiskType.getCoefficient(valueForDefault);
        BigDecimal alternateValue = fireRiskType.getCoefficient(valueForAlternate);

        //Assert
        assertThat(defaultValue, is(BigDecimal.valueOf(0.014)));
        assertThat(alternateValue, is(BigDecimal.valueOf(0.024)));
    }
}
