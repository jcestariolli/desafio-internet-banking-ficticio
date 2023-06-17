package internetbankingficticio.repository.account;

import internetbankingficticio.dao.account.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDao, String> {
}
