package hw.proofit.policycalculator.components.risktypepremiumcalculation;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.PolicyCalculation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MultiplySumByCoefficientTest {

    private MultiplySumByCoefficient component = new MultiplySumByCoefficient();

    @Test
    void multiply_sum_by_coefficient_test() {
        //Arrange
        List<PolicyCalculation> processedObjects = Arrays.asList(
                PolicyCalculation.create(RiskType.FIRE, BigDecimal.valueOf(30), BigDecimal.TEN),
                PolicyCalculation.create(RiskType.THEFT, BigDecimal.valueOf(120), BigDecimal.valueOf(5))
        );

        //Act
        List<PolicyCalculation> result = component.calculate(processedObjects);

        //Assert
        assertThat(result, containsInAnyOrder(
                allOf(hasProperty("riskType", is(RiskType.FIRE)), hasProperty("sum", is(BigDecimal.valueOf(300)))),
                allOf(hasProperty("riskType", is(RiskType.THEFT)), hasProperty("sum", is(BigDecimal.valueOf(600))))
        ));
    }

    @Test
    void handle_empty_list_test() {
        //Act
        List<PolicyCalculation> result = component.calculate(new ArrayList<>());

        //Assert
        assertThat(result, is(empty()));
    }

    @Test
    void handle_null_test() {
        //Assert
        assertDoesNotThrow(() -> component.calculate(null));
    }
}