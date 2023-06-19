package internetbankingficticio.repository.transaction;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.repository.account.AccountRepository;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountDaoObject;
import static internetbankingficticio.test.utils.transaction.TransactionObjectsTestUtils.generateTransactionDaoObject;
import static internetbankingficticio.utils.DateUtils.getDateByString;
import static internetbankingficticio.utils.DateUtils.getDateNow;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TransactionRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Should find Transaction by TransactionId")
    void shouldFindTransactionById() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));

        Optional<TransactionDao> foundTransaction = transactionRepository.findById(transactionDao1.getId());

        assertThat(foundTransaction).isPresent().get().isEqualTo(transactionDao1);
    }

    @Test
    @DisplayName("Should find Transactions by Period")
    void shouldFindTransactionsByPeriod() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findAllByExecutedOnBetween(getDateByString("2023-06-02"), getDateByString("2023-06-03"));
        assertThat(transactionDaoList).isNotEmpty().size().isEqualTo(2);
        assertThat(transactionDaoList.get(0)).isEqualTo(transactionDao2);
        assertThat(transactionDaoList.get(1)).isEqualTo(transactionDao3);
    }

    @Test
    @DisplayName("Should find all Transactions by AccountId")
    void shouldFindAllTransactionsByAccountId() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        AccountDao account2 = testEntityManager.persist(generateAccountDaoObject("abcdef", ammount, true));
        testEntityManager.persist(generateTransactionDaoObject(account2, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        testEntityManager.persist(generateTransactionDaoObject(account2, TransactionCommand.WITHDRAW, new BigDecimal(25), getDateByString("2023-06-02")));

        List<TransactionDao> transactionDaoList = transactionRepository.findAllByAccountIdAndExecutedOnBetween(account1.getId(), getDateByString("2023-06-02"), getDateByString("2023-06-03"));

        assertThat(transactionDaoList).hasSize(2).contains(transactionDao2, transactionDao3);
    }

    @Test
    @DisplayName("Should find all Transactions by AccountId and Period")
    void shouldFindAllTransactionsByAccountIdAndPeriod() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        TransactionDao transactionDao4 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findByAccountId(account1.getId());

        assertThat(transactionDaoList).hasSize(4).contains(transactionDao1, transactionDao2, transactionDao3, transactionDao4);
    }

    @Test
    @DisplayName("Should find all Transactions when Repository has data")
    void shouldFindAllTransactions_whenRepositoryHasData() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        TransactionDao transactionDao4 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findAll();
        assertThat(transactionDaoList).isNotEmpty().hasSize(4).contains(transactionDao1, transactionDao2, transactionDao3, transactionDao4);
    }

    @Test
    @DisplayName("Should not find any Transaction when Repository is empty")
    void shouldNotFindAnyTransaction_whenRepositoryIsEmpty() {
        List<TransactionDao> transactionDaoList = transactionRepository.findAll();
        assertThat(transactionDaoList).isEmpty();
    }

    @Test
    @DisplayName("Should store a Transaction")
    void shouldStoreTransaction() {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao = generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateNow());
        TransactionDao createdTransactionDao = transactionRepository.save(transactionDao);
        assertThat(createdTransactionDao).isEqualTo(transactionDao);
    }

    @Test
    @DisplayName("Should update the Transaction by TransactionId")
    void shouldUpdateTransactionById() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDaoToUpdate = transactionRepository.findById(transactionDao1.getId()).get();
        transactionDaoToUpdate.setCommand(TransactionCommand.WITHDRAW);
        transactionDaoToUpdate.setAmmount(new BigDecimal(50));

        transactionRepository.save(transactionDaoToUpdate);
        TransactionDao storedTransactionDao = transactionRepository.findById(transactionDao1.getId()).get();

        assertThat(storedTransactionDao.getCommand()).isEqualTo(transactionDaoToUpdate.getCommand());
        assertThat(storedTransactionDao.getAmmount()).isEqualTo(transactionDaoToUpdate.getAmmount());
        assertThat(storedTransactionDao.getExecutedOn()).isEqualTo(transactionDao1.getExecutedOn());

    }

    @Test
    @DisplayName("Should delete the Transaction by TransactionId")
    void shouldDeleteTransactionById() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));

        transactionRepository.deleteById(transactionDao2.getId());

        List<TransactionDao> transactionDaoList = transactionRepository.findAll();
        assertThat(transactionDaoList).hasSize(1).contains(transactionDao1);
    }

    @Test
    @DisplayName("Should delete all Transactions")
    void shouldDeleteAllTransactions() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));
        assertThat(transactionRepository.findAll()).isNotEmpty();

        transactionRepository.deleteAll();

        assertThat(transactionRepository.findAll()).isEmpty();
    }

}
