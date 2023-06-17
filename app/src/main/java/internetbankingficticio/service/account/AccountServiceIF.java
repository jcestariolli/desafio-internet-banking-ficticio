package internetbankingficticio.service.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;

import java.util.List;
import java.util.Optional;

public interface AccountServiceIF {
    List<AccountDto> listAllAccounts();

    Optional<AccountDto> findAccountById(String accountId);

    boolean existsById(String accountId);

    AccountDto createAccount(AccountCreateDto accountDto);

    Optional<AccountDto> updateAccount(String accountId, AccountUpdateDto accountDto);
}
