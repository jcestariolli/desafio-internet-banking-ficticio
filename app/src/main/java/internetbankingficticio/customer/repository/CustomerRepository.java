package internetbankingficticio.customer.repository;

import internetbankingficticio.customer.dao.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, Long> {
}
