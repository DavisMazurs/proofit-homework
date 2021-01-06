package hw.proofit.policycalculator.api;

import hw.proofit.policycalculator.PolicyValidationException;
import hw.proofit.policycalculator.model.Policy;
import hw.proofit.policycalculator.PolicyCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policy")
public class PolicyRestController {

    private final PolicyCalculator policyCalculator;

    @Autowired
    PolicyRestController(PolicyCalculator policyCalculator) {
        this.policyCalculator = policyCalculator;
    }

    @PostMapping("/calculate-premium")
    public ResponseEntity<Response> calculatePremium(@RequestBody Policy policy) {
        try {
            String premium = policyCalculator.calculate(policy);
            return new ResponseEntity<>(new ResponseSuccess(premium), HttpStatus.OK);
        } catch (PolicyValidationException e) {
            return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
