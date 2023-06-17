package internetbankingficticio.dao.customeraccount;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import internetbankingficticio.dao.account.AccountDao;
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
public class CustomerAccountDao extends AbstractInternetBankingDao {

    @Id
    private Long customerId;

    @Id
    @OneToOne
    private AccountDao accountId;
}
