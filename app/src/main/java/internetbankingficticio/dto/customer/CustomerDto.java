package internetbankingficticio.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import internetbankingficticio.dto.AbstractInternetBankingDto;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto extends AbstractInternetBankingDto {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private Date birthday;
}
