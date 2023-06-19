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
public class CustomerDto implements AbstractInternetBankingDto {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Sao_Paulo")
    private Date birthday;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        CustomerDto other = (CustomerDto) obj;
        if (this.getId() != other.getId()) return false;
        if (this.getName() == null ? (other.getName() != null) : !(this.getName().equals(other.getName())))
            return false;
        return (this.getBirthday() == null ? (other.getBirthday() == null) : (this.getBirthday().getDay() == other.getBirthday().getDay() && this.getBirthday().getMonth() == other.getBirthday().getMonth() && this.getBirthday().getYear() == other.getBirthday().getYear()));
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (int) this.getId();
        result = prime * result + ((this.getName() == null) ? 0 : this.getName().hashCode());
        result = prime * result + ((this.getBirthday() == null) ? 0 : this.getBirthday().hashCode());
        return result;
    }
}
