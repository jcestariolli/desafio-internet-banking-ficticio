package internetbankingficticio.service.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.EntityNotFoundException;
import internetbankingficticio.exception.ValidationException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountServiceIF {
    List<AccountDto> listAllAccounts();

    Optional<AccountDto> findAccountById(String accountId);

    boolean existsById(String accountId);

    AccountDto createAccount(AccountCreateDto accountDto);

    Optional<AccountDto> updateAccount(String accountId, AccountUpdateDto accountDto);

    AccountDto updateAccountBalance(String accountId, BigDecimal ammout) throws EntityNotFoundException;

    List<TransactionDto> findAllTransactionsByAccountId(String accountId);

    List<TransactionDto> findAllByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd);

    TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException, ValidationException;
}
