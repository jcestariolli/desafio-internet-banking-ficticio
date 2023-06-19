package internetbankingficticio.dto.customeraccount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAccountCreateDto implements AbstractInternetBankingDto {
    @JsonProperty(value = "cliente", required = true)
    CustomerCreateDto customerDto;
    @JsonProperty(value = "conta", required = true)
    AccountCreateDto accountDto;
}
