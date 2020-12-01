package hw.proofit.policycalculator;

import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyObject;
import hw.proofit.policycalculator.model.PolicySubObject;
import hw.proofit.policycalculator.enums.PolicyStatus;
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
    public void first_test_case() {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject theftSubObject = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(8), RiskType.THEFT);
        PolicyObject policyObject = PolicyObject.create("Home", Arrays.asList(fireSubObject, theftSubObject));
        Policy policy = Policy.create("Some Policy Number", PolicyStatus.APPROVED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("2.28 EUR", result);
    }

    @Test
    public void second_test_case() {
        //Arrange
        PolicySubObject fireSubObjectOne = PolicySubObject.create("TV", BigDecimal.valueOf(125), RiskType.FIRE);
        PolicySubObject fireSubObjectTwo = PolicySubObject.create("Expensive Furniture", BigDecimal.valueOf(200), RiskType.FIRE);
        PolicySubObject fireSubObjectThree = PolicySubObject.create("Phone", BigDecimal.valueOf(75), RiskType.FIRE);
        PolicySubObject fireSubObjectFour = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject theftSubObjectOne = PolicySubObject.create("TV", BigDecimal.valueOf(56.12), RiskType.THEFT);
        PolicySubObject theftSubObjectTwo = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(37.89), RiskType.THEFT);
        PolicySubObject theftSubObjectThree = PolicySubObject.create("Laptop", BigDecimal.valueOf(8.50), RiskType.THEFT);

        PolicyObject policyObject = PolicyObject.create("Home",
                Arrays.asList(fireSubObjectOne,
                        fireSubObjectTwo,
                        fireSubObjectThree,
                        fireSubObjectFour,
                        theftSubObjectOne,
                        theftSubObjectTwo,
                        theftSubObjectThree));

        Policy policy = Policy.create("Some Policy Number", PolicyStatus.APPROVED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("17.13 EUR", result);
    }

    @Test
    public void custom_test_case_one() {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject fireSubObject2 = PolicySubObject.create("TV", BigDecimal.valueOf(256.00), RiskType.FIRE);
        PolicySubObject fireSubObject3 = PolicySubObject.create("TV", BigDecimal.valueOf(50), RiskType.FIRE);
        PolicySubObject theftSubObject = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(8), RiskType.THEFT);
        PolicySubObject theftSubObject2 = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(24.13), RiskType.THEFT);
        PolicySubObject theftSubObject3 = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(32.26), RiskType.THEFT);
        PolicyObject policyObject = PolicyObject.create("Home", Arrays.asList(fireSubObject, theftSubObject, fireSubObject2, theftSubObject2, fireSubObject3, theftSubObject3));
        Policy policy = Policy.create("Some Policy Number", PolicyStatus.APPROVED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("12.96 EUR", result);
    }

    @Test
    public void custom_test_case_two() {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create("TV", BigDecimal.valueOf(100), RiskType.FIRE);
        PolicySubObject fireSubObject2 = PolicySubObject.create("TV", BigDecimal.valueOf(256.00), RiskType.FIRE);
        PolicySubObject fireSubObject3 = PolicySubObject.create("TV", BigDecimal.valueOf(50), RiskType.FIRE);
        PolicyObject policyObject = PolicyObject.create("Home", Arrays.asList(fireSubObject, fireSubObject2, fireSubObject3));
        Policy policy = Policy.create("Some Policy Number", PolicyStatus.APPROVED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("9.74 EUR", result);
    }

    @Test
    public void doesnt_allow_negative_numbers() {
        //Arrange
        PolicySubObject fireSubObject = PolicySubObject.create("TV", BigDecimal.valueOf(-1), RiskType.FIRE);
        PolicySubObject theftSubObject = PolicySubObject.create("Coffee Maker", BigDecimal.valueOf(8), RiskType.THEFT);
        PolicyObject policyObject = PolicyObject.create("Home", Arrays.asList(fireSubObject, theftSubObject));
        Policy policy = Policy.create("Some Policy Number", PolicyStatus.APPROVED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("Error: Insured sum must not contain negative numbers", result);
    }

    @Test
    public void doesnt_crash_if_subobjects_are_null() {
        //Arrange
        PolicyObject policyObject = PolicyObject.create("Work", null);
        Policy policy = Policy.create("Policy Number", PolicyStatus.REGISTERED, Collections.singletonList(policyObject));

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("0.00 EUR", result);
    }

    @Test
    public void policy_has_no_policy_object() {
        //Arrange
        Policy policy = Policy.create("Policy number", PolicyStatus.APPROVED, null);

        //Act
        String result = policyCalculator.calculate(policy);

        //Assert
        assertEquals("Error: Policy must contain at least 1 policy object", result);
    }
}