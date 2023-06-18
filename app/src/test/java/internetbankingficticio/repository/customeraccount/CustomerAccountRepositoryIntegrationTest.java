package internetbankingficticio.repository.customeraccount;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountIdDaoKey;
import internetbankingficticio.repository.account.AccountRepository;
import internetbankingficticio.repository.customer.CustomerRepository;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDaoObject;
import static internetbankingficticio.utils.DateUtils.getDateNow;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerAccountRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Should not find any CustomerAccountDao when Repository is empty")
    public void shouldNotFindAnyCustomerAccount_whenRepositoryIsEmpty() {
        List<CustomerAccountDao> customerAccountList = customerAccountRepository.findAll();

        assertThat(customerAccountList).isEmpty();
    }

    @Test
    @DisplayName("Should find all CustomerAccounts when Repository has data")
    public void shouldFindAllCustomerAccounts_whenRepositoryHasData() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerDao customerDaoAbc = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoAbc);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(customerDaoAbc).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);

        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerDao customerDaoXPTO = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(customerDaoXPTO).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);

        List<CustomerAccountDao> customerAccountList = customerAccountRepository.findAll();

        assertThat(customerAccountList).hasSize(2).contains(customerAccountAbc, customerAccountXpto);
    }

    @Test
    @DisplayName("Should find CustomerAccountDao by CustomerAccountId")
    public void shouldFindCustomerAccountByCustomerAccountId() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerDao customerDaoAbc = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoAbc);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(customerDaoAbc).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerDao customerDaoXPTO = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(customerDaoXPTO).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);


        CustomerAccountIdDaoKey customerAccountId = CustomerAccountIdDaoKey.builder().customerId(customerAccountXpto.getCustomerId().getId()).accountId(customerAccountXpto.getAccountId().getId()).build();
        Optional<CustomerAccountDao> foundCustomerAccountDao = customerAccountRepository.findById(customerAccountId);

        assertThat(foundCustomerAccountDao).isPresent();
        assertThat(foundCustomerAccountDao.get()).isEqualTo(customerAccountXpto);
    }

    @Test
    @DisplayName("Should find CustomerAccountDao by Customer")
    public void shouldFindCustomerAccountByCustomer() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerDao customerDaoAbc = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoAbc);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(customerDaoAbc).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerDao customerDaoXPTO = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(customerDaoXPTO).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);

        List<CustomerAccountDao> foundCustomerAccountDao = customerAccountRepository.findByCustomerId(customerAccountXpto.getCustomerId());

        assertThat(foundCustomerAccountDao).size().isEqualTo(1);
        assertThat(foundCustomerAccountDao.get(0)).isEqualTo(customerAccountXpto);
    }

    @Test
    @DisplayName("Should find CustomerAccountDao by AccountId")
    public void shouldFindCustomerAccountByAccountId() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerDao customerDaoAbc = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoAbc);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(customerDaoAbc).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerDao customerDaoXPTO = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerDaoXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(customerDaoXPTO).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);

        List<CustomerAccountDao> foundCustomerAccountDao = customerAccountRepository.findByAccountId(customerAccountXpto.getAccountId());

        assertThat(foundCustomerAccountDao).size().isEqualTo(1);
        assertThat(foundCustomerAccountDao.get(0)).isEqualTo(customerAccountXpto);
    }

    @Test
    @DisplayName("Should store the CustomerAccount")
    public void shouldStoreCustomerAccount() {
        AccountDao accountIdTestSingle = AccountDao.builder().id("accountIdTestSingle").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountIdTestSingle);
        CustomerDao customerTestSingle = generateCustomerDaoObject("Customer Test 1");
        testEntityManager.persist(customerTestSingle);

        CustomerAccountDao customerAccountDao = CustomerAccountDao.builder().customerId(customerTestSingle).accountId(accountIdTestSingle).build();

        CustomerAccountDao storedCustomerAccountDao = customerAccountRepository.save(customerAccountDao);

        assertThat(storedCustomerAccountDao).hasFieldOrPropertyWithValue("customerId", customerAccountDao.getCustomerId());
        assertThat(storedCustomerAccountDao).hasFieldOrPropertyWithValue("accountId", customerAccountDao.getAccountId());
    }

    @Test
    @DisplayName("Should find all Accounts by CustomerId")
    public void shouldFindAllAccountsByCustomerId() {
        AccountDao account1 = AccountDao.builder().id("accountIdTestYYY").balance(new BigDecimal("100.00")).exclusivePlan(true).build();
        AccountDao account2 = AccountDao.builder().id("accountIdTestZZZ").balance(new BigDecimal("50.10")).exclusivePlan(false).build();
        testEntityManager.persist(account1);
        testEntityManager.persist(account2);
        CustomerDao customerDao = testEntityManager.persist(CustomerDao.builder().name("Customer Test").birthday(getDateNow()).build());
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerDao).accountId(account1).build());
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerDao).accountId(account2).build());

        List<CustomerAccountDao> accountList = customerAccountRepository.findByCustomerId(customerDao);

        assertThat(accountList).isNotEmpty();
        assertThat(accountList.size()).isEqualTo(2);
        assertThat(accountList.get(0).getAccountId()).isEqualTo(account1);
        assertThat(accountList.get(1).getAccountId()).isEqualTo(account2);
    }

    @Test
    @DisplayName("Should find all Accounts")
    public void shouldFindAllCustomerAccounts() {
        AccountDao account1 = AccountDao.builder().id("accountIdTestYYY").balance(new BigDecimal("100.00")).exclusivePlan(true).build();
        AccountDao account2 = AccountDao.builder().id("accountIdTestZZZ").balance(new BigDecimal("50.10")).exclusivePlan(false).build();
        testEntityManager.persist(account1);
        testEntityManager.persist(account2);
        CustomerDao customerDao = testEntityManager.persist(CustomerDao.builder().name("Customer Test").birthday(getDateNow()).build());
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerDao).accountId(account1).build());
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerDao).accountId(account2).build());

        List<CustomerAccountDao> accountList = customerAccountRepository.findAll();

        assertThat(accountList).isNotEmpty();
        assertThat(accountList.size()).isEqualTo(2);
    }

}
