package hw.proofit.policycalculator;

import hw.proofit.policycalculator.PolicyCalculator;
import hw.proofit.policycalculator.PolicyValidationException;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyObject;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.enums.RiskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PolicyCalculatorTest {

    @Autowired
    PolicyCalculator policyCalculator;

    @Test
    void first_test_case() throws PolicyValidationException {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create(BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject theftSubObject = PolicySubObject.create(BigDecimal.valueOf(8), RiskType.THEFT);
        PolicyObject policyObject = PolicyObject.create(Arrays.asList(fireSubObject, theftSubObject));
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("2.28 EUR", result);
    }

    @Test
    void second_test_case() throws PolicyValidationException {
        //Arrange
        PolicySubObject fireSubObjectOne = PolicySubObject.create(BigDecimal.valueOf(125), RiskType.FIRE);
        PolicySubObject fireSubObjectTwo = PolicySubObject.create(BigDecimal.valueOf(200), RiskType.FIRE);
        PolicySubObject fireSubObjectThree = PolicySubObject.create(BigDecimal.valueOf(75), RiskType.FIRE);
        PolicySubObject fireSubObjectFour = PolicySubObject.create(BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject theftSubObjectOne = PolicySubObject.create(BigDecimal.valueOf(56.12), RiskType.THEFT);
        PolicySubObject theftSubObjectTwo = PolicySubObject.create(BigDecimal.valueOf(37.89), RiskType.THEFT);
        PolicySubObject theftSubObjectThree = PolicySubObject.create(BigDecimal.valueOf(8.50), RiskType.THEFT);

        PolicyObject policyObject = PolicyObject.create(
                Arrays.asList(fireSubObjectOne,
                        fireSubObjectTwo,
                        fireSubObjectThree,
                        fireSubObjectFour,
                        theftSubObjectOne,
                        theftSubObjectTwo,
                        theftSubObjectThree));

        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("17.13 EUR", result);
    }

    @Test
    void custom_test_case_one() throws PolicyValidationException {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create(BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject fireSubObject2 = PolicySubObject.create(BigDecimal.valueOf(256.00), RiskType.FIRE);
        PolicySubObject fireSubObject3 = PolicySubObject.create(BigDecimal.valueOf(50), RiskType.FIRE);
        PolicySubObject theftSubObject = PolicySubObject.create(BigDecimal.valueOf(8), RiskType.THEFT);
        PolicySubObject theftSubObject2 = PolicySubObject.create(BigDecimal.valueOf(24.13), RiskType.THEFT);
        PolicySubObject theftSubObject3 = PolicySubObject.create(BigDecimal.valueOf(32.26), RiskType.THEFT);
        PolicyObject policyObject = PolicyObject.create(Arrays.asList(fireSubObject, theftSubObject, fireSubObject2, theftSubObject2, fireSubObject3, theftSubObject3));
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("12.96 EUR", result);
    }

    @Test
    void custom_test_case_two() throws PolicyValidationException {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create(BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject fireSubObject2 = PolicySubObject.create(BigDecimal.valueOf(256.00), RiskType.FIRE);
        PolicySubObject fireSubObject3 = PolicySubObject.create(BigDecimal.valueOf(50), RiskType.FIRE);
        PolicyObject policyObject = PolicyObject.create(Arrays.asList(fireSubObject, fireSubObject2, fireSubObject3));
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("9.74 EUR", result);
    }

    @Test
    void doesnt_crash_if_subobjects_are_null() throws PolicyValidationException {
        //Arrange
        PolicyObject policyObject = PolicyObject.create(null);
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("0.00 EUR", result);
    }
}