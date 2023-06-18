package internetbankingficticio.service.transaction.withdraw;

import internetbankingficticio.dto.account.AccountDto;

import java.math.BigDecimal;

public interface WithdrawTransactionCalculatorIF {

    BigDecimal applyBusinessRuleInTransactionAmount(BigDecimal transactionAmmount, AccountDto accountDto);

    BigDecimal calculateAccountBalanceAfterTransaction(BigDecimal accountBalance, BigDecimal transactionAmmout);
}
