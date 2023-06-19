package internetbankingficticio.dto.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.serializer.MoneySerializer;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto extends AbstractInternetBankingDto {

    @JsonProperty(value = "id")
    private long id;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        TransactionDto other = (TransactionDto) obj;
        if (this.getId() != other.getId()) return false;
        if (this.getAccountId() == null ? (other.getAccountId() != null) : !(this.getAccountId().equals(other.getAccountId())))
            return false;
        if (this.getAmmount() == null ? (other.getAmmount() != null) : !(this.getAmmount().equals(other.getAmmount())))
            return false;
        if (this.getCommand() == null ? (other.getCommand() != null) : !(this.getCommand().equals(other.getCommand())))
            return false;
        return (this.getExecutedOn() == null ? (other.getExecutedOn() == null) : (this.getExecutedOn().getDay() == other.getExecutedOn().getDay() && this.getExecutedOn().getMonth() == other.getExecutedOn().getMonth() && this.getExecutedOn().getYear() == other.getExecutedOn().getYear()));
    }
}
