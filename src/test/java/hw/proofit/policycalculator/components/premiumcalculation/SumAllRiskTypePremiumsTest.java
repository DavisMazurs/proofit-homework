package hw.proofit.policycalculator.components.premiumcalculation;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.PolicyCalculation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SumAllRiskTypePremiumsTest {

    private SumAllRiskTypePremiums component = new SumAllRiskTypePremiums();

    @Test
    void sum_all_risk_type_premiums_test() {
        //Arrange
        List<PolicyCalculation> riskTypePremiums = Arrays.asList(
                PolicyCalculation.create(RiskType.FIRE, BigDecimal.valueOf(66.50)),
                PolicyCalculation.create(RiskType.THEFT, BigDecimal.valueOf(78.04))
        );

        //Act
        BigDecimal result = component.calculate(riskTypePremiums);

        //Assert
        assertThat(result, is(BigDecimal.valueOf(144.54)));
    }

    @Test
    void handle_empty_list_test() {
        //Act
        BigDecimal result = component.calculate(new ArrayList<>());

        //Assert
        assertThat(result, is(BigDecimal.ZERO));
    }

    @Test
    void handle_null_test() {
        //Assert
        assertDoesNotThrow(() -> component.calculate(null));
    }
}