package hw.proofit.policycalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Some boilerplate code for easier work with BigDecimal
 *  I only added methods, that are used in this task, but there could be others,
 *  like lessThan, lessThanOrEqual and so on
 */

public class BigDecimalUtils {

    private BigDecimalUtils() {
        //Utility class
    }

    public static boolean isGreaterThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }

    public static boolean isGreaterThanOrEqual(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) >= 0;
    }

    public static boolean isLessThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) < 0;
    }

    public static String formatMoney(BigDecimal value){
        if (value == null)
            return null;

        return value.setScale(2, RoundingMode.HALF_UP) + " EUR";
    }
}
