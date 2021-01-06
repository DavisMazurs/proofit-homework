package hw.proofit.policycalculator.services;

import hw.proofit.policycalculator.builder.PolicyBuildingObject;
import hw.proofit.policycalculator.builder.PolicyBuildingPatterns;
import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculator;
import hw.proofit.policycalculator.components.premiumcalculation.PremiumCalculatorFactory;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculator;
import hw.proofit.policycalculator.components.risktypepremiumcalculation.RiskTypeCalculatorFactory;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculator;
import hw.proofit.policycalculator.components.subobjectcalculation.SubObjectCalculatorFactory;
import hw.proofit.policycalculator.enums.RiskType;
import hw.proofit.policycalculator.model.*;
import hw.proofit.policycalculator.risktypes.PolicyRiskType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanFactory;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PolicyServiceTest {

    @InjectMocks
    private PolicyService policyService;

    @Mock
    private BeanFactory beanFactory;
    @Mock
    private PolicyRiskType policyRiskType;
    @Mock
    private ComponentService componentService;

    @BeforeEach
    void setup() {
        when(beanFactory.getBean(anyString(), eq(PolicyRiskType.class))).thenReturn(policyRiskType);
    }

    @Test
    void calculate_premium_test() {
        //Arrange
        List<PolicyCalculation> calculatedSubObjects = getCalculatedSubObjects(null);
        List<PolicyCalculation> riskTypePremiums = getCalculatedSubObjects(BigDecimal.TEN);
        PolicyBuildingObject builder = PolicyBuildingPatterns.defaultPattern(Policy.create(new ArrayList<>()));

        when(componentService.subObjectCalculation(anyMap(), any())).thenReturn(calculatedSubObjects);
        when(componentService.riskTypeCalculation(eq(calculatedSubObjects), any())).thenReturn(riskTypePremiums);
        when(componentService.premiumCalculation(eq(riskTypePremiums), any())).thenReturn(BigDecimal.valueOf(42));

        //Act
        BigDecimal endResult = policyService.calculatePremium(builder);

        //Assert
        ArgumentCaptor<List<PolicyCalculation>> captor = ArgumentCaptor.forClass(List.class);
        verify(componentService).subObjectCalculation(anyMap(), any());
        verify(beanFactory, times(2)).getBean(anyString(), eq(PolicyRiskType.class));
        verify(policyRiskType, times(2)).getCoefficient(any());
        verify(componentService).riskTypeCalculation(captor.capture(), any());
        verify(componentService).premiumCalculation(eq(riskTypePremiums), any());
        assertThat(captor.getValue().size(), is(2));
        assertThat(captor.getValue(), Matchers.containsInAnyOrder(
                hasProperty("riskType", is(RiskType.FIRE)),
                hasProperty("riskType", is(RiskType.THEFT))
        ));
        assertThat(endResult, is(BigDecimal.valueOf(42)));
    }

    @Test
    void calculate_only_for_one_risk_type() {
        //Arrange
        List<PolicyCalculation> calculatedSubObjects = getCalculatedSubObjects(null);
        PolicyBuildingObject builder = PolicyBuildingPatterns.defaultPattern(Policy.create(new ArrayList<>()), RiskType.FIRE);

        when(componentService.subObjectCalculation(anyMap(), any())).thenReturn(calculatedSubObjects);
        when(policyRiskType.getCoefficient(any())).thenReturn(BigDecimal.TEN);

        //Act
        policyService.calculatePremium(builder);

        //Assert
        ArgumentCaptor<List<PolicyCalculation>> captor = ArgumentCaptor.forClass(List.class);
        verify(componentService).riskTypeCalculation(captor.capture(), any());
        assertThat(captor.getValue().size(), is(1));
        PolicyCalculation result = captor.getValue().get(0);
        assertThat(result.getRiskType(), is(RiskType.FIRE));
    }

    private List<PolicyCalculation> getCalculatedSubObjects(BigDecimal coefficient) {
        return Arrays.asList(
                PolicyCalculation.create(RiskType.FIRE, BigDecimal.TEN, coefficient != null ? coefficient : BigDecimal.ZERO),
                PolicyCalculation.create(RiskType.THEFT, BigDecimal.TEN, coefficient != null ? coefficient : BigDecimal.ZERO)
        );
    }
}