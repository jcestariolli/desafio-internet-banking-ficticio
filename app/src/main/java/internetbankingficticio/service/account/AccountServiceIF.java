package internetbankingficticio.service.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountServiceIF {
    List<AccountDto> listAllAccounts();

    Optional<AccountDto> findAccountById(String accountId);

    boolean existsById(String accountId);

    AccountDto createAccount(AccountCreateDto accountDto);

    Optional<AccountDto> updateAccount(String accountId, AccountUpdateDto accountDto);

    AccountDto updateAccountBalance(String accountId, BigDecimal ammout) throws EntityNotFoundException;


}
