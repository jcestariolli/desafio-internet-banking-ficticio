package internetbankingficticio.dao.customeraccount;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.customer.CustomerDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customer_account")
@IdClass(CustomerAccountIdDaoKey.class)
public class CustomerAccountDao implements AbstractInternetBankingDao {

    @Id
    @OneToOne
    private CustomerDao customerId;

    @Id
    @OneToOne
    private AccountDao accountId;
}
