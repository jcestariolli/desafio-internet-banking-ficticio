package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
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
import java.util.List;
import java.util.Optional;

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountDtoObject;
import static internetbankingficticio.test.utils.account.AccountServiceMockTestUtils.*;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDtoObject;
import static internetbankingficticio.test.utils.customer.CustomerServiceMockTestUtils.*;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountObjectsTestUtils.generateCustomerAccountCreateDtoObject;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountObjectsTestUtils.generateCustomerAccountDaoObject;
import static internetbankingficticio.test.utils.customeraccount.CustomerAccountRepositoryMockTestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CustomerAccountServiceUnitTest extends AbstractTest {

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
    @DisplayName("Should return Account List when findAccountsByCustomerId() finds the Customer")
    public void shouldReturnAccountList_whenFindAccountsByCustomerIdFindsCustomer() {
        Long customerId = 1L;
        mockServiceFindCustomerByIdWithCustomer(customerService, customerId, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByCustomerIdWithCustomerAccountList(customerAccountRepository, new ArrayList<>());

        Optional<List<AccountDto>> accountDtoList = customerAccountService.findAccountsByCustomerId(customerId);
        assertThat(accountDtoList).isPresent();
    }

    @Test
    @DisplayName("Should return Empty Result when findAccountsByCustomerId() does not find the Customer")
    public void shouldReturnEmptyResult_whenFindAccountsByCustomerIdDoesNotFindCustomer() {
        Long customerId = 1L;
        mockServiceFindCustomerByIdWithEmptyResult(customerService, customerId);
        Optional<List<AccountDto>> accountDtoList = customerAccountService.findAccountsByCustomerId(customerId);
        assertThat(accountDtoList).isEmpty();
    }

    @Test
    @DisplayName("Should return Customer List when findCustomersByAccountId() finds the Account")
    public void shouldReturnCustomerList_whenFindCustomersByAccountIdFindsAccount() {
        String accountId = "12345678";
        mockServiceFindAccountByIdWithAccount(accountService, accountId, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockRepositoryFindByAccountIdWithCustomerAccountList(customerAccountRepository, new ArrayList<>());

        Optional<List<CustomerDto>> customerDtoList = customerAccountService.findCustomersByAccountId(accountId);
        assertThat(customerDtoList).isPresent();
    }

    @Test
    @DisplayName("Should return Empty Result when findCustomersByAccountId() does not find the Account")
    public void shouldReturnEmptyResult_whenFindCustomersByAccountIdDoesNotFindAccount() {
        String accountId = "12345678";
        mockServiceFindAccountByIdWithEmptyResult(accountService, accountId);
        Optional<List<CustomerDto>> customerDtoList = customerAccountService.findCustomersByAccountId(accountId);
        assertThat(customerDtoList).isEmpty();
    }

    @Test
    @DisplayName("Should return CustomerAccount when createCustomerWithAccount() for existing CustomerAccount")
    public void shouldReturnCustomerAccount_whenCreateCustomerWithAccountForExistingCustomerAccount() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();
        CustomerAccountDao customerAccountDao = generateCustomerAccountDaoObject();

        mockServiceCreateAccountWithAccount(accountService, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockServiceCreateCustomerWithCustomer(customerService, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByIdWithCustomerAccount(customerAccountRepository, customerAccountDao);

        CustomerAccountDto customerAccountDto = customerAccountService.createCustomerWithAccount(customerAccountCreateDto);
        assertThat(customerAccountDto).isNotNull();

    }

    @Test
    @DisplayName("Should return CustomerAccount when createCustomerWithAccount() for new CustomerAccount")
    public void shouldReturnCustomerAccount_whenCreateCustomerWithAccountForNewCustomerAccount() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();

        mockServiceCreateAccountWithAccount(accountService, generateAccountDtoObject(accountId, BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true));
        mockServiceCreateCustomerWithCustomer(customerService, generateCustomerDtoObject(customerId, "Customer Test 1"));
        mockRepositoryFindByIdWithEmptyResult(customerAccountRepository);
        mockRepositorySaveWithCustomerAccount(customerAccountRepository, generateCustomerAccountDaoObject());

        CustomerAccountDto customerAccountDto = customerAccountService.createCustomerWithAccount(customerAccountCreateDto);
        assertThat(customerAccountDto).isNotNull();
    }

    @Test
    @DisplayName("Should return CustomerAccount List when listAll()")
    public void shouldReturnCustomerAccountList_whenListAll() {
        String accountId = "12345678";
        Long customerId = 1L;
        CustomerAccountCreateDto customerAccountCreateDto = generateCustomerAccountCreateDtoObject();

        mockRepositoryFindAllWithCustomerAccountList(customerAccountRepository, List.of(generateCustomerAccountDaoObject()));

        List<CustomerAccountDto> customerAccountDtoList = customerAccountService.listAllCustomerAccounts();
        assertThat(customerAccountDtoList.size()).isEqualTo(1);
    }

}
