package hw.proofit.policycalculator.risktypes;

import hw.proofit.policycalculator.utils.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("THEFT")
public class TheftRiskType implements PolicyRiskType {

    private BigDecimal defaultValue;
    private BigDecimal alternateValue;
    private BigDecimal conditionValue;

    @Autowired
    public TheftRiskType(@Value("${risktype.theft.default.value}") BigDecimal defaultValue,
                         @Value("${risktype.theft.alternate.value}") BigDecimal alternateValue,
                         @Value("${risktype.theft.condition.value}") BigDecimal conditionValue) {
        this.defaultValue = defaultValue;
        this.alternateValue = alternateValue;
        this.conditionValue = conditionValue;
    }

    @Override
    public BigDecimal getCoefficient(BigDecimal sum) {
        return BigDecimalUtils.isGreaterThanOrEqual(sum, conditionValue) ? alternateValue : defaultValue;
    }
}
