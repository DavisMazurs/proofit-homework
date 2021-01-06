package hw.proofit.policycalculator.risktypes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class TheftRiskTypeTest {

    @Autowired
    private TheftRiskType theftRiskType;

    @Test
    void theft_type_coefficient_test() {
        //Arrange
        BigDecimal valueForDefault = BigDecimal.valueOf(10);
        BigDecimal valueForAlternate = BigDecimal.valueOf(15);

        //Act
        BigDecimal defaultValue = theftRiskType.getCoefficient(valueForDefault);
        BigDecimal alternateValue = theftRiskType.getCoefficient(valueForAlternate);

        //Assert
        assertThat(defaultValue, is(BigDecimal.valueOf(0.11)));
        assertThat(alternateValue, is(BigDecimal.valueOf(0.05)));
    }
}
