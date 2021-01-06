package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.PolicyValidationException;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.model.PolicyObject;
import hw.proofit.policycalculator.model.PolicySubObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PolicyValidationServiceTest {

    @Autowired
    PolicyValidationService policyValidationService;

    @Test
    void zero_objects_handled() {
        //Arrange
        Policy policy = Policy.create(new ArrayList<>());

        //Act
        Exception exception = assertThrows(
                PolicyValidationException.class,
                () -> policyValidationService.validate(policy)
        );

        //Assert
        assertThat(exception.getMessage(), is("Error: Policy must contain at least 1 policy object"));
    }

    @Test
    void negative_number_handled() {
        //Arrange
        PolicySubObject policySubObject = PolicySubObject.create(BigDecimal.valueOf(-100), RiskType.FIRE);
        PolicyObject policyObject = PolicyObject.create(Collections.singletonList(policySubObject));
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Act
        Exception exception = assertThrows(
                PolicyValidationException.class,
                () -> policyValidationService.validate(policy)
        );

        //Assert
        assertThat(exception.getMessage(), is("Error: Insured sum must not contain negative numbers"));
    }

    @Test
    void policy_is_null_handled() {
        //Arrange
        Policy policy = null;

        //Act
        Exception exception = assertThrows(
                PolicyValidationException.class,
                () -> policyValidationService.validate(policy)
        );

        //Assert
        assertThat(exception.getMessage(), is("Error: Policy must not be null"));
    }

    @Test
    void should_pass() {
        //Arrange
        PolicySubObject policySubObject = PolicySubObject.create(BigDecimal.valueOf(100), RiskType.FIRE);
        PolicyObject policyObject = PolicyObject.create(Collections.singletonList(policySubObject));
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Assert
        assertDoesNotThrow(() -> policyValidationService.validate(policy));
    }

    @Test
    void should_pass_with_null_subobject() {
        //Arrange
        PolicyObject policyObject = PolicyObject.create(null);
        Policy policy = Policy.create(Collections.singletonList(policyObject));

        //Assert
        assertDoesNotThrow(() -> policyValidationService.validate(policy));
    }
}