package internetbankingficticio.dao.transaction;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.transaction.converter.TransactionCommandConverter;
import internetbankingficticio.enums.transaction.TransactionCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transaction")
public class TransactionDao extends AbstractInternetBankingDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private AccountDao account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionCommandConverter.class)
    private TransactionCommand command;

    @Column(nullable = false)
    private BigDecimal ammount;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedOn;
}
