package internetbankingficticio.test.utils.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.service.account.AccountServiceIF;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AccountServiceMockTestUtils {

    public static void mockServiceListAllAccountsWithAccountList(AccountServiceIF accountServiceMock, List<AccountDto> accountDtoList) {
        when(accountServiceMock.listAllAccounts()).thenReturn(accountDtoList);
    }

    public static void mockServiceFindAccountByIdWithAccount(AccountServiceIF accountServiceMock, String accountId, AccountDto accountDto) {
        when(accountServiceMock.findAccountById(accountId)).thenReturn(Optional.of(accountDto));
    }

    public static void mockServiceFindAccountByIdWithEmptyResult(AccountServiceIF accountServiceMock, String accountId) {
        when(accountServiceMock.findAccountById(accountId)).thenReturn(Optional.empty());
    }

    public static void mockServiceCreateAccountWithAccount(AccountServiceIF accountServiceMock, AccountDto accountDto) {
        when(accountServiceMock.createAccount(any(AccountCreateDto.class))).thenReturn(accountDto);
    }

    public static void mockServiceUpdateAccountWithAccount(AccountServiceIF accountServiceMock, AccountDto accountDto) {
        when(accountServiceMock.updateAccount(anyString(), any(AccountUpdateDto.class))).thenReturn(Optional.of(accountDto));
    }

    public static void mockServiceUpdateAccountWithEmptyResult(AccountServiceIF accountServiceMock, String accountId, AccountUpdateDto accountUpdateDto) {
        when(accountServiceMock.updateAccount(accountId, accountUpdateDto)).thenReturn(Optional.empty());
    }
}
