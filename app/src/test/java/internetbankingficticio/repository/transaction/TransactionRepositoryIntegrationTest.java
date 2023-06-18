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
    @DisplayName("Should store a Transaction")
    public void shouldStoreTransaction() {
        BigDecimal ammount = new BigDecimal(100);
        AccountDao account1 = testEntityManager.persist(generateAccountDaoObject("12345678", ammount, true));

        TransactionDao transactionDao = generateTransactionDaoObject(account1, TransactionCommand.DEPOSIT, ammount, getDateNow());

        TransactionDao createdTransactionDao = transactionRepository.save(transactionDao);

        assertThat(createdTransactionDao).isEqualTo(transactionDao);

    }

}
