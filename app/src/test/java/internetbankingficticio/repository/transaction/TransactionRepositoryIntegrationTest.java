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

import static internetbankingficticio.test.utils.TestUtils.getDateByString;
import static internetbankingficticio.test.utils.TestUtils.getDateNow;
import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountDaoObject;
import static internetbankingficticio.test.utils.transaction.TransactionObjectsTestUtils.generateTransactionDaoObject;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TransactionRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Should find Transaction by TransactionId")
    public void shouldFindTransactionById() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));

        Optional<TransactionDao> foundTransaction = transactionRepository.findById(transactionDao1.getId());

        assertThat(foundTransaction).isPresent();
        assertThat(foundTransaction.get()).isEqualTo(transactionDao1);
    }

    @Test
    @DisplayName("Should find Transactions by Period")
    public void shouldFindTransactionsByPeriod() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findAllByExecutedOnBetween(getDateByString("2023-06-02"), getDateByString("2023-06-03"));
        assertThat(transactionDaoList).isNotEmpty();
        assertThat(transactionDaoList.size()).isEqualTo(2);
        assertThat(transactionDaoList.get(0)).isEqualTo(transactionDao2);
        assertThat(transactionDaoList.get(1)).isEqualTo(transactionDao3);
    }

    @Test
    @DisplayName("Should find all Transactions by AccountId")
    public void shouldFindAllTransactionsByAccountId() throws ParseException {
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

        assertThat(transactionDaoList).size().isEqualTo(2);
        assertThat(transactionDaoList).contains(transactionDao2, transactionDao3);
    }

    @Test
    @DisplayName("Should find all Transactions by AccountId and Period")
    public void shouldFindAllTransactionsByAccountIdAndPeriod() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        TransactionDao transactionDao4 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findByAccountId(account1.getId());

        assertThat(transactionDaoList).size().isEqualTo(4);
        assertThat(transactionDaoList).contains(transactionDao1, transactionDao2, transactionDao3, transactionDao4);
    }

    @Test
    @DisplayName("Should find all Transactions when Repository has data")
    public void shouldFindAllTransactions_whenRepositoryHasData() throws ParseException {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao1 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateByString("2023-06-01")));
        TransactionDao transactionDao2 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, new BigDecimal(50), getDateByString("2023-06-02")));
        TransactionDao transactionDao3 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-03")));
        TransactionDao transactionDao4 = testEntityManager.persist(generateTransactionDaoObject(account1, TransactionCommand.WITHDRAW, new BigDecimal("10.10"), getDateByString("2023-06-04")));

        List<TransactionDao> transactionDaoList = transactionRepository.findAll();
        assertThat(transactionDaoList).isNotEmpty();
        assertThat(transactionDaoList.size()).isEqualTo(4);
        assertThat(transactionDaoList).contains(transactionDao1, transactionDao2, transactionDao3, transactionDao4);
    }

    @Test
    @DisplayName("Should not find any Transaction when Repository is empty")
    public void shouldNotFindAnyTransaction_whenRepositoryIsEmpty() {
        List<TransactionDao> transactionDaoList = transactionRepository.findAll();
        assertThat(transactionDaoList).isEmpty();
    }

    @Test
    @DisplayName("Should store a Transaction")
    public void shouldStoreTransaction() {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));
        TransactionDao transactionDao = generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateNow());

        TransactionDao createdTransactionDao = transactionRepository.save(transactionDao);

        assertThat(createdTransactionDao).isEqualTo(transactionDao);
    }

    @Test
    @DisplayName("Should update the Transaction by TransactionId")
    public void shouldUpdateTransactionById() throws ParseException {
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
    public void shouldDeleteTransactionById() throws ParseException {
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
    public void shouldDeleteAllTransactions() throws ParseException {
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
