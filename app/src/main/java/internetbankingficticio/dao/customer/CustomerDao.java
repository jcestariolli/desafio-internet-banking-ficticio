package internetbankingficticio.dao.customer;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customer")
public class CustomerDao extends AbstractInternetBankingDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;
}
