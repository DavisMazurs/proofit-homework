package hw.proofit.policycalculator.utils;

import hw.proofit.policycalculator.utils.BigDecimalUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BigDecimalUtilsTest {

    @Test
    void is_greater_than_test() {
        assertTrue(BigDecimalUtils.isGreaterThan(BigDecimal.TEN, BigDecimal.ONE));
        assertFalse(BigDecimalUtils.isGreaterThan(BigDecimal.TEN, BigDecimal.TEN));
        assertFalse(BigDecimalUtils.isGreaterThan(BigDecimal.ONE, BigDecimal.TEN));
    }

    @Test
    void is_greater_than_or_equal_test() {
        assertTrue(BigDecimalUtils.isGreaterThanOrEqual(BigDecimal.TEN, BigDecimal.ONE));
        assertTrue(BigDecimalUtils.isGreaterThanOrEqual(BigDecimal.TEN, BigDecimal.TEN));
        assertFalse(BigDecimalUtils.isGreaterThanOrEqual(BigDecimal.ONE, BigDecimal.TEN));
    }

    @Test
    void is_less_than_test() {
        assertFalse(BigDecimalUtils.isLessThan(BigDecimal.TEN, BigDecimal.ONE));
        assertFalse(BigDecimalUtils.isLessThan(BigDecimal.TEN, BigDecimal.TEN));
        assertTrue(BigDecimalUtils.isLessThan(BigDecimal.ONE, BigDecimal.TEN));
    }

    @Test
    void format_money_test() {
        assertThat(BigDecimalUtils.formatMoney(BigDecimal.TEN), is("10.00 EUR"));
        assertThat(BigDecimalUtils.formatMoney(BigDecimal.ZERO), is("0.00 EUR"));
        assertThat(BigDecimalUtils.formatMoney(BigDecimal.valueOf(56.378)), is("56.38 EUR"));
        assertThat(BigDecimalUtils.formatMoney(BigDecimal.valueOf(73.273)), is("73.27 EUR"));
    }
}