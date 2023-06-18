package internetbankingficticio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Internet Banking Ficticio")
                        .description("Desafio Técnico de um Internet Banking Fictício, com API Rest e base em memória")
                        .version("1.0"));
    }
}