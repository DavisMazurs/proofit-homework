package hw.proofit.policycalculator.components.subobjectcalculation;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.PolicyCalculation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class SumOfRiskTypeAmountsTest {

    private SumOfRiskTypeAmounts component = new SumOfRiskTypeAmounts();

    @Test
    void sum_of_risk_type_amounts_test() {
        //Arrange
        List<BigDecimal> fireTypeSums = Arrays.asList(
                BigDecimal.valueOf(23.04),
                BigDecimal.valueOf(42.17),
                BigDecimal.valueOf(97.23)
        );
        List<BigDecimal> theftTypeSums = Arrays.asList(
                BigDecimal.valueOf(23.08),
                BigDecimal.valueOf(57.47),
                BigDecimal.valueOf(16.13)
        );
        Map<RiskType, List<BigDecimal>> groupedSubObjects = new HashMap<>();
        groupedSubObjects.put(RiskType.FIRE, fireTypeSums);
        groupedSubObjects.put(RiskType.THEFT, theftTypeSums);

        //Act
        List<PolicyCalculation> result = component.calculate(groupedSubObjects);

        //Assert
        assertThat(result, containsInAnyOrder(
                allOf(hasProperty("riskType", is(RiskType.FIRE)),
                        hasProperty("sum", is(BigDecimal.valueOf(162.44)))),
                allOf(hasProperty("riskType", is(RiskType.THEFT)),
                        hasProperty("sum", is(BigDecimal.valueOf(96.68))))
        ));
    }

    @Test
    void handle_empty_map_test() {
        //Act
        List<PolicyCalculation> result = component.calculate(new HashMap<>());

        //Assert
        assertThat(result, is(empty()));
    }

    @Test
    void handle_null_test() {
        //Assert
        assertDoesNotThrow(() -> component.calculate(null));
    }
}