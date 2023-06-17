package internetbankingficticio.service.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
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

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.*;
import static internetbankingficticio.test.utils.account.AccountRepositoryMockTestUtils.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountServiceIntegrationTest extends AbstractTest {
    @Autowired
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapperMock;
    @MockBean
    AccountCreateDtoToAccountDaoMapper accountCreateDtoToAccountDaoMapperMock;
    @MockBean
    AccountUpdateDtoToAccountDaoMapper accountUpdateDtoToAccountDaoMapperMock;

    @Test
    @DisplayName("Should call repository findAll() List when listAllAccounts()")
    public void shouldCallRepositoryFindAll_whenListAllAccounts() {
        accountService.listAllAccounts();
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should call repository findById() when findAccountById()")
    public void shouldCallRepositoryFindById_whenFindAccountById() {
        String accountTestId = "12345678";
        accountService.findAccountById(accountTestId);
        verify(accountRepository, times(1)).findById(accountTestId);
    }

    @Test
    @DisplayName("Should call repository existsById() when existsById()")
    public void shouldCallRepositoryExistsById_whenExistsById() {
        String accountTestId = "12345678";
        accountService.existsById(accountTestId);
        verify(accountRepository, times(1)).existsById(accountTestId);
    }

    @Test
    @DisplayName("Should call repository save() when createAccount()")
    public void shouldCallRepositorySave_whenCreateAccount() {
        String accountTestId = "12345678";
        AccountDao accountDao = generateAccountDaoObject(accountTestId, new BigDecimal(100), true);
        mockAccountCreateDtoToAccountDaoMapperMap(accountCreateDtoToAccountDaoMapperMock, accountDao);

        accountService.createAccount(generateAccountCreateDtoObject(accountTestId, new BigDecimal(100), true));

        verify(accountRepository, times(1)).save(accountDao);
    }

    @Test
    @DisplayName("Should call repository findById() and save() when updateAccount() finds the Account to update")
    public void shouldCallRepositoryFindByIdAndShouldCallSave_whenUpdateAccountFindsAccount() {
        String accountTestId = "12345678";
        AccountDao accountDao = generateAccountDaoObject(accountTestId, new BigDecimal(100), true);
        mockAccountUpdateDtoToAccountDaoMapperMap(accountUpdateDtoToAccountDaoMapperMock, accountDao);
        mockAccountDaoToAccountDtoMapperMap(accountDaoToAccountDtoMapperMock, AccountDto.builder().build());
        mockRepositoryFindByIdWithAccount(accountRepository, accountTestId, accountDao);

        accountService.updateAccount(accountTestId, generateAccountUpdateDtoObject(true));

        verify(accountRepository, times(1)).findById(accountTestId);
        verify(accountRepository, times(1)).save(accountDao);
    }

    @Test
    @DisplayName("Should call repository findById() and save() when updateAccount() does not find the Account to update")
    public void shouldCallRepositoryFindByIdAndShouldNotCallSave_whenUpdateAccountDoesNotFindAccountToUpdate() {
        String accountTestId = "12345678";
        mockRepositoryFindByIdWithEmptyResult(accountRepository, accountTestId);

        accountService.updateAccount(accountTestId, AccountUpdateDto.builder().build());

        verify(accountRepository, times(1)).findById(accountTestId);
        verify(accountRepository, times(0)).save(any(AccountDao.class));
    }

}
