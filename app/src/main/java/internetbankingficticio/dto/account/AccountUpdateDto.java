package internetbankingficticio.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountUpdateDto implements AbstractInternetBankingDto {

    @JsonProperty(value = "plano_exclusive", required = true, defaultValue = "false")
    private Boolean exclusivePlan;
}
