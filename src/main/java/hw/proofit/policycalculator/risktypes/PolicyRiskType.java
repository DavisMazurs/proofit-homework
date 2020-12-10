package hw.proofit.policycalculator.risktypes;

import java.math.BigDecimal;

public interface PolicyRiskType {
    BigDecimal getCoefficient(BigDecimal sum);
}
