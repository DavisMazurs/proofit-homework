# Proof IT Policy Calculator
##### Author: Dāvis Mazurs

## Description

This is a very simple policy calculator, that caluculates premium based on values that are defined in code. It's been made to be easily extendable if there's need for that (for example new risk types added). This is a very simple implementation, without any configuration files, and depencency injection exists only in test class.

## Risk Types and Coefficient Calculation

It's very easy to add new risk types, all you need to do - add the new value in RiskType enum, and then define logic for calculation multiplication coefficient, by adding a case to the switch:
> hw.proofit.policycalculator.CalculationProperties.java
```
    private static BigDecimal getCoefficient(RiskType riskType, BigDecimal sum) {
        switch (riskType) {
            case FIRE:
                return BigDecimalUtils.isGreaterThan(sum, BigDecimal.valueOf(100)) ? BigDecimal.valueOf(0.024) : BigDecimal.valueOf(0.014);
            case THEFT:
                return BigDecimalUtils.isGreaterThanOrEqual(sum, BigDecimal.valueOf(15)) ? BigDecimal.valueOf(0.05) : BigDecimal.valueOf(0.11);
            case NEW_CASE:
                //enter logic here
            default:
                return BigDecimal.ZERO;
        }
    }
```

## Validation

There is a simple validation in place, which checks if input is valid. Validation rules can easily be added, defining a new validation method, which adds an error message to the list of error messages in case on validation failing:
> hw.proofit.policycalculator.Validator.java
```
    private void validatePolicyHasAtleastOneObject(Policy policy) {
        if (policy.getPolicyObjects() == null || policy.getPolicyObjects().isEmpty()) {
            errorMessages.add("Error: Policy must contain at least 1 policy object");
        }
    }
    
    private void newValidationRule() {
        if(//some_criteria) {
            errorMessages.add("Error message")
        }
    }
```

## Premium Calculation

Most processes are made to be reusable and extendable, however the premium calculation rules were very specific in task description, and since it was the only required way of calculating premium, the output is produced by this block of code:
> hw.proofit.policycalculator.PolicyCalculator.java
```
        return BigDecimalUtils.formatMoney(
                riskTypePremiums.getOrDefault(RiskType.FIRE, BigDecimal.ZERO)
                .add(riskTypePremiums.getOrDefault(RiskType.THEFT, BigDecimal.ZERO)));
```

In this case this is ok, but if there would be other possibilities of how premium was calculated, this would need to be refactored - probably moved to some utility class that contains all the calculation options.