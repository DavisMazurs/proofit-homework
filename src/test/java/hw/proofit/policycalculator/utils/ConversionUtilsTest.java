package hw.proofit.policycalculator.utils;

import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyObject;
import hw.proofit.policycalculator.model.PolicySubObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasEntry;

class ConversionUtilsTest {

    @Test
    void get_subobjects_test() {
        //Arrange
        List<PolicySubObject> policySubObjects = getSubObjects();
        PolicyObject policyObject = PolicyObject.create(policySubObjects);
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        List<PolicySubObject> resultSubObjects = ConversionUtils.getAllSubObjects(policy);

        //Assert
        assertThat(resultSubObjects.size(), is(5));
        assertThat(resultSubObjects, is(policySubObjects));
    }

    @Test
    void group_subobjects_test() {
        //Act
        Map<RiskType, List<BigDecimal>> groupedSubObjects = ConversionUtils.groupSubObjects(getSubObjects());

        //Assert
        assertThat(groupedSubObjects.size(), is(2));
        assertThat(groupedSubObjects, hasEntry(is(RiskType.FIRE), containsInAnyOrder(
                BigDecimal.TEN,
                BigDecimal.valueOf(46),
                BigDecimal.valueOf(57.02))));
        assertThat(groupedSubObjects, hasEntry(is(RiskType.THEFT), containsInAnyOrder(
                BigDecimal.TEN,
                BigDecimal.valueOf(76.32))));
    }

    private List<PolicySubObject> getSubObjects() {
        return Arrays.asList(
                PolicySubObject.create(BigDecimal.TEN, RiskType.FIRE),
                PolicySubObject.create(BigDecimal.valueOf(46), RiskType.FIRE),
                PolicySubObject.create(BigDecimal.valueOf(57.02), RiskType.FIRE),
                PolicySubObject.create(BigDecimal.TEN, RiskType.THEFT),
                PolicySubObject.create(BigDecimal.valueOf(76.32), RiskType.THEFT)
        );
    }
}