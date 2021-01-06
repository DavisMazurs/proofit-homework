package hw.proofit.policycalculator.builder;

import hw.proofit.policycalculator.enums.componenttypes.PremiumCalculation;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.enums.componenttypes.RiskTypeCalculation;
import hw.proofit.policycalculator.enums.componenttypes.SubObjectCalculation;
import hw.proofit.policycalculator.model.Policy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

class PolicyBuildingPatternsTest {

    @Test
    void default_pattern_test() {
        PolicyBuildingObject builder = PolicyBuildingPatterns.defaultPattern(Policy.create(new ArrayList<>()));

        assertThat(builder.getSubObjectCalculation(), is(SubObjectCalculation.SUM_OF_RISK_TYPE_AMOUNTS));
        assertThat(builder.getRiskTypeCalculation(), is(RiskTypeCalculation.MULTIPLY_SUM_BY_COEFFICIENT));
        assertThat(builder.getPremiumCalculation(), is(PremiumCalculation.SUM_ALL_PREMIUMS));
        assertThat(builder.getRiskTypes(), is(empty()));
    }

    @Test
    void default_pattern_with_specific_type_test() {
        PolicyBuildingObject builder = PolicyBuildingPatterns.defaultPattern(Policy.create(new ArrayList<>()), RiskType.FIRE);

        assertThat(builder.getSubObjectCalculation(), is(SubObjectCalculation.SUM_OF_RISK_TYPE_AMOUNTS));
        assertThat(builder.getRiskTypeCalculation(), is(RiskTypeCalculation.MULTIPLY_SUM_BY_COEFFICIENT));
        assertThat(builder.getPremiumCalculation(), is(PremiumCalculation.SUM_ALL_PREMIUMS));
        assertThat(builder.getRiskTypes(), contains(RiskType.FIRE));
    }
}