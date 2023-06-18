package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountDtoToAccountDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customeraccount.CustomerAccountDaoToCustomerAccountDtoMapper;
import internetbankingficticio.repository.customeraccount.CustomerAccountRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.customer.CustomerServiceIF;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountDtoObject;
import static internetbankingficticio.test.utils.account.AccountServiceMockTestUtils.*;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDtoObject;
import static internetbankingficticio.test.utils.customer.CustomerServiceMockTestUtils.*;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountObjectsTestUtils.generateCustomerAccountCreateDtoObject;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountObjectsTestUtils.generateCustomerAccountDaoObject;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountRepositoryMockTestUtils.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerAccountServiceIntegrationTest extends AbstractTest {
    @Autowired
    CustomerAccountService customerAccountService;

    @MockBean
    CustomerServiceIF customerService;

    @MockBean
    AccountServiceIF accountService;

    @MockBean
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    AccountDtoToAccountDaoMapper accountDtoToAccountDaoMapper;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;
    @Autowired
    CustomerDtoToCustomerDaoMapper customerDtoToCustomerDaoMapper;
    @Autowired
    CustomerAccountDaoToCustomerAccountDtoMapper customerAccountDaoToCustomerAccountDtoMapper;

    @Test
    @DisplayName("Should call repository findByCustomerId() when finds the Customer")
    public void shouldCallRepositoryFindByCustomerId_whenFindsCustomer() {
        Long customerId = 1L;
        mockServiceFindCustomerByIdWithCustomer(customerService, customerId, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByCustomerIdWithCustomerAccountList(customerAccountRepository, new ArrayList<>());

        customerAccountService.findAccountsByCustomerId(customerId);
        verify(customerService, times(1)).findCustomerById(any());
        verify(customerAccountRepository, times(1)).findByCustomerId(any());
    }

    @Test
    @DisplayName("Should not call repository findByCustomerId() when does not find the Customer")
    public void shouldNotCallRepositoryFindByCustomerId_whenDoesNotFindCustomer() {
        Long customerId = 1L;
        mockServiceFindCustomerByIdWithEmptyResult(customerService, customerId);
        customerAccountService.findAccountsByCustomerId(customerId);
        verify(customerService, times(1)).findCustomerById(any());
        verify(customerAccountRepository, times(0)).findByCustomerId(any());
    }

    @Test
    @DisplayName("Should call repository findByAccountId() when finds the Account")
    public void shouldCallRepositoryFindByAccountId_whenFindsAccount() {
        String accountId = "12345678";
        mockServiceFindAccountByIdWithAccount(accountService, accountId, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockRepositoryFindByAccountIdWithCustomerAccountList(customerAccountRepository, new ArrayList<>());

        customerAccountService.findCustomersByAccountId(accountId);

        verify(accountService, times(1)).findAccountById(any());
        verify(customerAccountRepository, times(1)).findByAccountId(any());
    }

    @Test
    @DisplayName("Should not call repository findByAccountId() when does not find the Account")
    public void shouldNotCallRepositoryFindByAccountId_whenDoesNotFindAccount() {
        String accountId = "12345678";
        mockServiceFindAccountByIdWithEmptyResult(accountService, accountId);

        customerAccountService.findCustomersByAccountId(accountId);

        verify(accountService, times(1)).findAccountById(any());
        verify(customerAccountRepository, times(0)).findByAccountId(any());
    }

    @Test
    @DisplayName("Should create Customer and Account before verifying existing CustomerAccount")
    public void shouldCreateCustomerAndAccount_beforeVerifyingExistingCustomerAccount() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();
        CustomerAccountDao customerAccountDao = generateCustomerAccountDaoObject();

        mockServiceCreateAccountWithAccount(accountService, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockServiceCreateCustomerWithCustomer(customerService, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByIdWithCustomerAccount(customerAccountRepository, customerAccountDao);
        mockRepositorySaveWithCustomerAccount(customerAccountRepository, customerAccountDao);

        customerAccountService.createCustomerWithAccount(customerAccountCreateDto);
        verify(accountService, times(1)).createAccount(any());
        verify(customerService, times(1)).createCustomer(any());
    }

    @Test
    @DisplayName("Should create CustomerAccount when CustomerAccount does not exists")
    public void shouldCreateCustomerAccount_whenCustomerAccountDoesNotExists() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();
        CustomerAccountDao customerAccountDao = generateCustomerAccountDaoObject();

        mockServiceCreateAccountWithAccount(accountService, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockServiceCreateCustomerWithCustomer(customerService, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByIdWithEmptyResult(customerAccountRepository);
        mockRepositorySaveWithCustomerAccount(customerAccountRepository, customerAccountDao);

        customerAccountService.createCustomerWithAccount(customerAccountCreateDto);

        verify(customerAccountRepository, times(1)).findById(any());
        verify(customerAccountRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should not create CustomerAccount when CustomerAccount exists")
    public void shouldNotCreateCustomerAccount_whenCustomerAccountExists() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();
        CustomerAccountDao customerAccountDao = generateCustomerAccountDaoObject();

        mockServiceCreateAccountWithAccount(accountService, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockServiceCreateCustomerWithCustomer(customerService, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByIdWithCustomerAccount(customerAccountRepository, customerAccountDao);
        mockRepositorySaveWithCustomerAccount(customerAccountRepository, customerAccountDao);

        customerAccountService.createCustomerWithAccount(customerAccountCreateDto);
        verify(customerAccountRepository, times(1)).findById(any());
        verify(customerAccountRepository, times(0)).save(any());
    }


}
