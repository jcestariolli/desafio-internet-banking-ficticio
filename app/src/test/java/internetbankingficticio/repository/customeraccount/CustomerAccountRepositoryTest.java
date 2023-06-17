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

import static internetbankingficticio.test.utils.TestUtils.getDateNow;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerAccountRepositoryTest extends AbstractTest {

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
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(1L).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(2L).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);

        List<CustomerAccountDao> customerAccountList = customerAccountRepository.findAll();

        assertThat(customerAccountList).hasSize(2).contains(customerAccountAbc, customerAccountXpto);
    }

    @Test
    @DisplayName("Should find CustomerAccountDao by CustomerAccountId")
    public void shouldFindCustomerAccountByCustomerAccountId() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(1L).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(2L).accountId(accountXPTO).build();
        testEntityManager.persist(customerAccountXpto);

        CustomerAccountIdDaoKey customerAccountId = CustomerAccountIdDaoKey.builder().customerId(customerAccountXpto.getCustomerId()).accountId(customerAccountXpto.getAccountId().getId()).build();
        Optional<CustomerAccountDao> foundCustomerAccountDao = customerAccountRepository.findById(customerAccountId);

        assertThat(foundCustomerAccountDao).isPresent();
        assertThat(foundCustomerAccountDao.get()).isEqualTo(customerAccountXpto);
    }

    @Test
    @DisplayName("Should find CustomerAccountDao by CustomerId")
    public void shouldFindCustomerAccountByCustomerId() {
        AccountDao accountABC = AccountDao.builder().id("accountIdTestABC").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountABC);
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(1L).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(2L).accountId(accountXPTO).build();
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
        CustomerAccountDao customerAccountAbc = CustomerAccountDao.builder().customerId(1L).accountId(accountABC).build();
        testEntityManager.persist(customerAccountAbc);
        AccountDao accountXPTO = AccountDao.builder().id("accountIdTestXPTO").balance(BigDecimal.valueOf(10.0)).exclusivePlan(true).build();
        testEntityManager.persist(accountXPTO);
        CustomerAccountDao customerAccountXpto = CustomerAccountDao.builder().customerId(2L).accountId(accountXPTO).build();
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
        CustomerAccountDao customerAccountDao = CustomerAccountDao.builder().customerId(1L).accountId(accountIdTestSingle).build();

        CustomerAccountDao storedCustomerAccountDao = customerAccountRepository.save(customerAccountDao);

        assertThat(storedCustomerAccountDao).hasFieldOrPropertyWithValue("customerId", customerAccountDao.getCustomerId());
        assertThat(storedCustomerAccountDao).hasFieldOrPropertyWithValue("accountId", customerAccountDao.getAccountId());
    }

    @Test
    @DisplayName("Should find all Accounts by CustomerId")
    public void shouldFindAllAccountsByCustomerId() {
        String accountId1 = "accountIdTestYYY";
        String accountId2 = "accountIdTestZZZ";
        AccountDao account1 = AccountDao.builder().id(accountId1).balance(new BigDecimal("100.00")).exclusivePlan(true).build();
        AccountDao account2 = AccountDao.builder().id(accountId2).balance(new BigDecimal("50.10")).exclusivePlan(false).build();
        testEntityManager.persist(account1);
        testEntityManager.persist(account2);
        Long customerId = testEntityManager.persist(CustomerDao.builder().name("Customer Test").birthday(getDateNow()).build()).getId();
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerId).accountId(account1).build());
        testEntityManager.persist(CustomerAccountDao.builder().customerId(customerId).accountId(account2).build());

        List<CustomerAccountDao> accountList = customerAccountRepository.findByCustomerId(customerId);

        assertThat(accountList).isNotEmpty();
        assertThat(accountList.size()).isEqualTo(2);
        assertThat(accountList.get(0).getAccountId()).isEqualTo(account1);
        assertThat(accountList.get(1).getAccountId()).isEqualTo(account2);
    }

}
