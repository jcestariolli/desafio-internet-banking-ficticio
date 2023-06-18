package internetbankingficticio.repository.customeraccount;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountIdDaoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccountDao, CustomerAccountIdDaoKey> {

    List<CustomerAccountDao> findByCustomerId(CustomerDao customer);

    List<CustomerAccountDao> findByAccountId(AccountDao account);

}
