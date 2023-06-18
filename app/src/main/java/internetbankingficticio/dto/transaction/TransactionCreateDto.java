package internetbankingficticio.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.utils.serializer.MoneySerializer;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCreateDto extends AbstractInternetBankingDto {

    @JsonProperty(value = "numero_conta")
    private String accountId;

    @JsonProperty(value = "operacao", required = true)
    private TransactionCommand command;

    @JsonProperty(value = "valor", required = true)
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal ammount;

    @JsonProperty(value = "data_transacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private Date executedOn;

}
