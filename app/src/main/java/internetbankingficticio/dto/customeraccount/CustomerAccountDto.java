package internetbankingficticio.dto.customeraccount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAccountDto implements AbstractInternetBankingDto {
    @JsonProperty(value = "cliente", required = true)
    CustomerDto customerDto;
    @JsonProperty(value = "conta", required = true)
    AccountDto accountDto;
}
