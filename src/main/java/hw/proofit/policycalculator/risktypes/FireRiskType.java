package hw.proofit.policycalculator.risktypes;

import hw.proofit.policycalculator.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
