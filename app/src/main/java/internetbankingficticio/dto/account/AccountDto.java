package internetbankingficticio.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import internetbankingficticio.mapper.utils.MoneyDeserializer;
import internetbankingficticio.mapper.utils.MoneySerializer;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto extends AbstractInternetBankingDto {

    @JsonProperty(value = "numero_conta", required = true)
    private String id;

    @JsonProperty(value = "saldo", required = true)
    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    private BigDecimal balance;

    @JsonProperty(value = "plano_exclusive", defaultValue = "false")
    private Boolean exclusivePlan;
}
