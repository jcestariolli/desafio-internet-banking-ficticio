package internetbankingficticio.repository.transaction;

import internetbankingficticio.dao.transaction.TransactionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDao, Long> {
}
