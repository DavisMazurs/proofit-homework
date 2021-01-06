package hw.proofit.policycalculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw.proofit.policycalculator.PolicyCalculator;
import hw.proofit.policycalculator.PolicyValidationException;
import hw.proofit.policycalculator.model.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PolicyRestController.class)
class PolicyRestControllerTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String POLICY_CALCULATION_ENDPOINT = "/api/policy/calculate-premium";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PolicyCalculator policyCalculator;

    @Test
    void calculate_premium_endpoint_test() throws Exception {
        //Arrange
        Policy policy = Policy.create(new ArrayList<>());
        String result = "123 EUR";
        String json = MAPPER.writeValueAsString(policy);
        when(policyCalculator.calculate(any())).thenReturn(result);

        //Act
        mockMvc.perform(
                post(POLICY_CALCULATION_ENDPOINT)
                        .content(json)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
        //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premium", is(result)));
    }

    @Test
    void bad_request_test() throws Exception {
        //Arrange
        Policy policy = Policy.create(new ArrayList<>());
        String result = "Error: something bad happened";
        String json = MAPPER.writeValueAsString(policy);
        when(policyCalculator.calculate(any())).thenThrow(new PolicyValidationException(result));

        //Act
        mockMvc.perform(
                post(POLICY_CALCULATION_ENDPOINT)
                        .content(json)
                        .contentType(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
        //Assert
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is(result)));
    }
}