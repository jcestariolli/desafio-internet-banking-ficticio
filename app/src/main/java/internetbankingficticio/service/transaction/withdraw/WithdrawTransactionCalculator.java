package internetbankingficticio.service.transaction.withdraw;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.enums.transaction.WithdrawFeeFactorEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WithdrawTransactionCalculator implements WithdrawTransactionCalculatorIF {

    @Override
    public BigDecimal applyBusinessRuleInTransactionAmount(BigDecimal transactionAmmount, AccountDto accountDto) {
        if (accountDto.getExclusivePlan()) return transactionAmmount;
        return transactionAmmount.multiply(WithdrawFeeFactorEnum.getFeeFactorForAmount(transactionAmmount));
    }

    @Override
    public BigDecimal calculateAccountBalanceAfterTransaction(BigDecimal accountBalance, BigDecimal transactionAmmout) {
        return accountBalance.subtract(transactionAmmout);
    }
}
