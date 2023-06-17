package internetbankingficticio.repository.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Should not find any Account when Repository is empty")
    public void shouldNotFindAnyAccount_whenRepositoryIsEmpty() {
        List<AccountDao> accountList = accountRepository.findAll();

        assertThat(accountList).isEmpty();
    }

    @Test
    @DisplayName("Should find all Accounts when Repository has data")
    public void shouldFindAllAccounts_whenRepositoryHasData() {
        AccountDao account1 = testEntityManager.persist(AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build());
        AccountDao account2 = testEntityManager.persist(AccountDao.builder().id("00000001").balance(new BigDecimal(50)).exclusivePlan(false).build());
        AccountDao account3 = testEntityManager.persist(AccountDao.builder().id("99999999").balance(new BigDecimal("10.10")).exclusivePlan(true).build());
        AccountDao account4 = testEntityManager.persist(AccountDao.builder().id("abcdefgh").balance(new BigDecimal("0.5")).exclusivePlan(false).build());

        List<AccountDao> accountList = accountRepository.findAll();

        assertThat(accountList).hasSize(4).contains(account1, account2, account3, account4);
    }

    @Test
    @DisplayName("Should find Account by AccountId")
    public void shouldFindAccountById() {
        AccountDao accountXpto = testEntityManager.persist(AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build());

        Optional<AccountDao> foundAccount = accountRepository.findById(accountXpto.getId());

        assertThat(foundAccount).isPresent();
        assertThat(foundAccount.get()).isEqualTo(accountXpto);
    }

    @Test
    @DisplayName("Should store the Account")
    public void shouldStoreAccount() {
        AccountDao account = AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build();

        AccountDao storedAccount = accountRepository.save(account);

        assertThat(storedAccount).hasFieldOrPropertyWithValue("id", account.getId());
        assertThat(storedAccount).hasFieldOrPropertyWithValue("balance", account.getBalance());
        assertThat(storedAccount).hasFieldOrPropertyWithValue("exclusivePlan", account.getExclusivePlan());
    }

    @Test
    @DisplayName("Should update the Account by AccountId")
    public void shouldUpdateAccountById() {
        AccountDao account5 = testEntityManager.persist(AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build());

        AccountDao updatedAccount5 = AccountDao.builder().id("updated test5").balance(new BigDecimal("10.00")).exclusivePlan(false).build();
        AccountDao foundAccountToUpdate = accountRepository.findById(account5.getId()).get();
        foundAccountToUpdate.setBalance(updatedAccount5.getBalance());
        foundAccountToUpdate.setExclusivePlan(updatedAccount5.getExclusivePlan());

        accountRepository.save(foundAccountToUpdate);

        AccountDao storedAccountAfterUpdate = accountRepository.findById(account5.getId()).get();
        assertThat(storedAccountAfterUpdate.getId()).isEqualTo(account5.getId());
        assertThat(storedAccountAfterUpdate.getBalance()).isEqualTo(updatedAccount5.getBalance());
        assertThat(storedAccountAfterUpdate.getExclusivePlan()).isEqualTo(updatedAccount5.getExclusivePlan());
    }

    @Test
    @DisplayName("Should delete the Account by AccountId")
    public void shouldDeleteAccountById() {
        AccountDao account9 = testEntityManager.persist(AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build());
        AccountDao account10 = testEntityManager.persist(AccountDao.builder().id("00000001").balance(new BigDecimal(50)).exclusivePlan(false).build());

        accountRepository.deleteById(account10.getId());

        List<AccountDao> accountList = accountRepository.findAll();
        assertThat(accountList).hasSize(1).contains(account9);
    }

    @Test
    @DisplayName("Should delete all Accounts")
    public void shouldDeleteAllAccounts() {
        testEntityManager.persist(AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build());
        testEntityManager.persist(AccountDao.builder().id("00000001").balance(new BigDecimal(50)).exclusivePlan(false).build());
        testEntityManager.persist(AccountDao.builder().id("99999999").balance(new BigDecimal("10.10")).exclusivePlan(true).build());
        testEntityManager.persist(AccountDao.builder().id("abcdefgh").balance(new BigDecimal("0.5")).exclusivePlan(false).build());

        accountRepository.deleteAll();

        assertThat(accountRepository.findAll()).isEmpty();
    }

}
