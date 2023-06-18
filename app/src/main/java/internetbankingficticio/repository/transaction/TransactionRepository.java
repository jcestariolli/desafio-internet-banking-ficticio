package internetbankingficticio.repository.transaction;

import internetbankingficticio.dao.transaction.TransactionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {

    List<TransactionDao> findAllByExecutedOnBetween(Date executedOnStart, Date executedOnEnd);

    List<TransactionDao> findByAccountId(String accountId);
    List<TransactionDao> findAllByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd);
}
