package internetbankingficticio.test.utils.transaction;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.enums.transaction.TransactionCommand;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionObjectsTestUtils {

    public static TransactionDao generateTransactionDaoObject(AccountDao accountDao, TransactionCommand command, BigDecimal ammount, Date executedOn) {
        return TransactionDao.builder().account(accountDao).command(command).ammount(ammount).executedOn(executedOn).build();
    }


}
