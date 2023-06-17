package internetbankingficticio.dao.customeraccount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerAccountIdDaoKey implements Serializable {

    private Long customerId;

    private String accountId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        CustomerAccountIdDaoKey other = (CustomerAccountIdDaoKey) obj;
        if (this.getCustomerId() == null ? (other.getCustomerId() != null) : !(this.getCustomerId().equals(other.getCustomerId())))
            return false;
        return (this.getAccountId() == null ? (other.getAccountId() == null) : (this.getAccountId().equals(other.getAccountId())));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((this.getCustomerId() == null) ? 0 : this.getCustomerId().hashCode());
        result = prime * result + ((this.getAccountId() == null) ? 0 : this.getAccountId().hashCode());
        return result;
    }
}
