package internetbankingficticio.enums.transaction;

import internetbankingficticio.utils.BigDecimalUtils;

import java.math.BigDecimal;

public enum WithdrawFeeFactorEnum {
    LAYER1(BigDecimal.ZERO, new BigDecimal(1)), LAYER2(new BigDecimal(101), new BigDecimal("1.004")), LAYER3(new BigDecimal(301), new BigDecimal("1.01"));


    private final BigDecimal layerInitialValue;

    private final BigDecimal feeFactor;

    WithdrawFeeFactorEnum(BigDecimal layerInitialValue, BigDecimal feeFactor) {
        this.layerInitialValue = layerInitialValue;
        this.feeFactor = feeFactor;
    }

    public static BigDecimal getFeeFactorForAmount(BigDecimal amount) {
        if (BigDecimalUtils.lessThan(amount, LAYER2.layerInitialValue)) return LAYER1.feeFactor;
        if (BigDecimalUtils.lessThan(amount, LAYER3.layerInitialValue)) return LAYER2.feeFactor;
        return LAYER3.feeFactor;
    }
}
