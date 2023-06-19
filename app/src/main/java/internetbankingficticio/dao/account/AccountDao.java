package internetbankingficticio.dao.account;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "account")
public class AccountDao implements AbstractInternetBankingDao {

    @Id
    private String id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Boolean exclusivePlan;
}
