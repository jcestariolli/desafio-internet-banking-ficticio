package internetbankingficticio.test.utils.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.exception.AccountResourceNotFoundException;
import internetbankingficticio.exception.ResourceNotFoundException;
import internetbankingficticio.service.account.AccountServiceIF;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AccountServiceMockTestUtils {

    public static void mockServiceListAllAccountsWithAccountList(AccountServiceIF accountServiceMock, List<AccountDto> accountDtoList) {
        when(accountServiceMock.listAllAccounts()).thenReturn(accountDtoList);
    }

    public static void mockServiceFindAccountByIdWithAccount(AccountServiceIF accountServiceMock, String accountId, AccountDto accountDto) throws ResourceNotFoundException {
        when(accountServiceMock.findAccountById(accountId)).thenReturn(accountDto);
    }

    public static void mockServiceFindAccountByIdThrowAccountNotFoundExcept(AccountServiceIF accountServiceMock, String accountId) throws ResourceNotFoundException {
        when(accountServiceMock.findAccountById(accountId)).thenThrow(new AccountResourceNotFoundException(accountId));
    }

    public static void mockServiceCreateAccountWithAccount(AccountServiceIF accountServiceMock, AccountDto accountDto) {
        when(accountServiceMock.createAccount(any(AccountCreateDto.class))).thenReturn(accountDto);
    }

    public static void mockServiceUpdateAccountWithAccount(AccountServiceIF accountServiceMock, AccountDto accountDto) throws ResourceNotFoundException {
        when(accountServiceMock.updateAccount(anyString(), any(AccountUpdateDto.class))).thenReturn(accountDto);
    }

    public static void mockServiceUpdateAccountThrowAccountNotFoundExcept(AccountServiceIF accountServiceMock, String accountId, AccountUpdateDto accountUpdateDto) throws ResourceNotFoundException {
        when(accountServiceMock.updateAccount(accountId, accountUpdateDto)).thenThrow(new AccountResourceNotFoundException(accountId));
    }
}
