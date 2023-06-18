package internetbankingficticio.controller.adviser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponsePojo {

    @JsonProperty(value = "status")
    private int status;

    @JsonProperty(value = "mensagem")
    private String message;

    @JsonProperty(value = "path")
    private String path;
}
