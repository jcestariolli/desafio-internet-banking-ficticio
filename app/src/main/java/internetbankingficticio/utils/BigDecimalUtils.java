package internetbankingficticio.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
    private BigDecimalUtils() {
    }

    public static boolean greaterThan(BigDecimal d1, BigDecimal d2) {
        return d1.compareTo(d2) > 0;
    }

    public static boolean greaterThanOrEquals(BigDecimal d1, BigDecimal d2) {
        return d1.compareTo(d2) >= 0;
    }

    public static boolean greaterThanOrEqualsToZero(BigDecimal d1) {
        return greaterThanOrEquals(d1, BigDecimal.ZERO);
    }

    public static boolean greaterThanZero(BigDecimal d1) {
        return greaterThan(d1, BigDecimal.ZERO);
    }

    public static boolean equalsZero(BigDecimal d1) {
        return BigDecimal.ZERO.compareTo(d1) == 0;
    }

    public static boolean lessThan(BigDecimal d1, BigDecimal d2) {
        return d1.compareTo(d2) < 0;
    }

    public static boolean lessThanOrEquals(BigDecimal d1, BigDecimal d2) {
        return d1.compareTo(d2) <= 0;
    }

    public static boolean lessThanZero(BigDecimal d1) {
        return lessThan(d1, BigDecimal.ZERO);
    }

    public static boolean lessThanOrEqualsToZero(BigDecimal d1) {
        return lessThanOrEquals(d1, BigDecimal.ZERO);
    }

}
