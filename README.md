## Update
###### version 2

Major refactoring, and redesigning system core. Also moved all the calculation logic into one class "PolicyCalculator" which is a Service, and contains main calculate() method, as well as a private method for calculating premium. Is was designed in a way to add more premium calculation implementations if needed, but currently there is only one:
> hw.proofit.policycalculator.PolicyCalculator.java
```
private BigDecimal multiplySumByCoefficient(RiskType type, BigDecimal sum) {
    PolicyRiskType riskType = beanFactory.getBean(type.name().toUpperCase(), PolicyRiskType.class);
    BigDecimal coefficient = riskType.getCoefficient(sum);
    return sum.multiply(coefficient);
}
```


## Adding new risk types

The biggest change is the way how risk types are added/processed. Using dependency injection and bean factory (see above in the premium calculation method), the system calculates the correct value depending on parameters given inside implementation of each type.

It is possible to define parameters in application.properties file, and then inject them into the specific risk type implementation:
> application.properties
```
#Fire Risk Type properties
risktype.fire.default.value=0.014
risktype.fire.alternate.value=0.024
risktype.fire.condition.value=100
```
```
@Autowired
public FireRiskType(@Value("${risktype.fire.default.value}") BigDecimal defaultValue,
                    @Value("${risktype.fire.alternate.value}") BigDecimal alternateValue,
                    @Value("${risktype.fire.condition.value}") BigDecimal conditionValue)
```

To add a new type, first you must add it to the RiskType enum:
> hw.proofit.policycalculator.enums.RiskType.java
```
public enum RiskType {
    FIRE,
    THEFT,
    NEW_TYPE
}
```

After that, simply create a new class for your type, which implements the PolicyRiskType interface, make it a component, and adjust it to your needs. Currently it contains only one method, that retuns the correct coefficient for the given type:
> NOTE: Component name must be exactly the same as it's enum name. It is not case sensitive.
> hw.proofit.policycalculator.risktypes.FireRiskType.java
```
@Component("FIRE")
public class FireRiskType implements PolicyRiskType {

    private BigDecimal defaultValue;
    private BigDecimal alternateValue;
    private BigDecimal conditionValue;

    @Autowired
    public FireRiskType(@Value("${risktype.fire.default.value}") BigDecimal defaultValue,
                        @Value("${risktype.fire.alternate.value}") BigDecimal alternateValue,
                        @Value("${risktype.fire.condition.value}") BigDecimal conditionValue) {
        this.defaultValue = defaultValue;
        this.alternateValue = alternateValue;
        this.conditionValue = conditionValue;
    }

    @Override
    public BigDecimal getCoefficient(BigDecimal sum) {
        return BigDecimalUtils.isGreaterThan(sum, conditionValue) ? alternateValue : defaultValue;
    }
}
```