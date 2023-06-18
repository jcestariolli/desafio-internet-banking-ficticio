package internetbankingficticio.service.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.exception.entity.AccountEntityNotFoundException;
import internetbankingficticio.mapper.account.AccountCreateDtoToAccountDaoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountUpdateDtoToAccountDaoMapper;
import internetbankingficticio.repository.account.AccountRepository;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.*;
import static internetbankingficticio.test.utils.account.AccountRepositoryMockTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceUnitTest extends AbstractTest {

    @Autowired
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;
    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    AccountCreateDtoToAccountDaoMapper accountCreateDtoToAccountDaoMapper;
    @Autowired
    AccountUpdateDtoToAccountDaoMapper accountUpdateDtoToAccountDaoMapper;


    @Test
    @DisplayName("Should return Account List when listAllAccounts()")
    public void shouldReturnAccountList_whenListAllAccounts() {
        List<AccountDao> accountDaoList = generateAccountDaoListObject();
        mockRepositoryFindAllWithAccountList(accountRepository, accountDaoList);

        List<AccountDto> expectedAccountDtoList = accountDaoList.stream().map(accountDao -> accountDaoToAccountDtoMapper.map(accountDao)).collect(Collectors.toList());
        List<AccountDto> returnedAccountDtoList = accountService.listAllAccounts();

        assertThat(returnedAccountDtoList).isNotEmpty();
        assertThat(returnedAccountDtoList).hasSize(expectedAccountDtoList.size());
        assertThat(returnedAccountDtoList).containsExactlyElementsOf(expectedAccountDtoList);
    }

    @Test
    @DisplayName("Should return Account when findAccountById() finds an Account")
    public void shouldReturnAccount_whenFindAccountByIdFindsAccount() throws AccountEntityNotFoundException {
        String accountTestId = "12345678";
        AccountDao accountDao = generateAccountDaoObject(accountTestId, new BigDecimal(100), true);
        mockRepositoryFindByIdWithAccount(accountRepository, accountTestId, accountDao);

        AccountDto returnedAccountDtoOpt = accountService.findAccountById(accountTestId);
        assertThat(returnedAccountDtoOpt).isEqualTo(accountDaoToAccountDtoMapper.map(accountDao));
    }

    @Test
    @DisplayName("Should throw AccountEntityNotFoundException when findAccountById() does not find an Account")
    public void shouldThrowAccountEntityNotFoundException_whenFindAccountByIdDoesNotFindAccount() {
        String accountTestId = "12345678";
        mockRepositoryFindByIdWithEmptyResult(accountRepository, accountTestId);
        assertThrows(AccountEntityNotFoundException.class, () -> {
            accountService.findAccountById(accountTestId);
        });
    }

    @Test
    @DisplayName("Should return true when existsById() finds an Account")
    public void shouldReturnTrue_whenExistsByIdFindsAccount() {
        String accountTestId = "12345678";
        mockRepositoryExistsByIdWithBoolean(accountRepository, accountTestId, true);
        assertThat(accountService.existsById(accountTestId)).isTrue();
    }

    @Test
    @DisplayName("Should return false when existsById() does not find an Account")
    public void shouldReturnFalse_whenExistsByIdDoesNotFindAccount() {
        String accountTestId = "12345678";
        mockRepositoryExistsByIdWithBoolean(accountRepository, accountTestId, false);
        assertThat(accountService.existsById(accountTestId)).isFalse();
    }

    @Test
    @DisplayName("Should return Account when createAccount()")
    public void shouldReturnAccount_whenCreateAccount() {
        String accountTestId = "12345678";
        AccountDao accountDao = generateAccountDaoObject(accountTestId, new BigDecimal(100), true);
        mockRepositorySaveWithAccount(accountRepository, accountDao);
        AccountDto returnedAccountDto = accountService.createAccount(generateAccountCreateDtoObject(accountTestId, new BigDecimal(100), true));
        assertThat(returnedAccountDto).isEqualTo(accountDaoToAccountDtoMapper.map(accountDao));
    }

    @Test
    @DisplayName("Should return Account when updateAccount() finds the Account to update")
    public void shouldReturnAccount_whenUpdateAccountFindsTheAccountToUpdate() throws AccountEntityNotFoundException {
        String accountTestId = "12345678";
        AccountDao accountDao = generateAccountDaoObject(accountTestId, new BigDecimal(100), true);
        mockRepositoryFindByIdWithAccount(accountRepository, accountTestId, accountDao);
        mockRepositorySaveWithAccount(accountRepository, accountDao);
        AccountDto returnedAccountDtoOpt = accountService.updateAccount(accountTestId, generateAccountUpdateDtoObject(true));
        assertThat(returnedAccountDtoOpt).isEqualTo(accountDaoToAccountDtoMapper.map(accountDao));
    }

    @Test
    @DisplayName("Should throw AccountEntityNotFoundException when updateAccount() does not find the Account to update")
    public void shouldThrowAccountEntityNotFoundException_whenUpdateAccountDoesNotFindTheAccountToUpdate() {
        String accountTestId = "12345678";
        mockRepositoryFindByIdWithEmptyResult(accountRepository, accountTestId);
        assertThrows(AccountEntityNotFoundException.class, () -> {
            accountService.updateAccount(accountTestId, AccountUpdateDto.builder().build());
        });
    }
}
