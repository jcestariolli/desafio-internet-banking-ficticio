package internetbankingficticio.service.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface AccountServiceIF {
    List<AccountDto> listAllAccounts();

    AccountDto findAccountById(String accountId) throws ResourceNotFoundException;

    boolean existsById(String accountId);

    AccountDto createAccount(AccountCreateDto accountDto);

    AccountDto updateAccount(String accountId, AccountUpdateDto accountDto) throws ResourceNotFoundException;

    AccountDto updateAccountBalance(String accountId, BigDecimal ammout) throws ResourceNotFoundException;


}
