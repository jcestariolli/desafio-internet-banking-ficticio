package internetbankingficticio.config.customer;

import internetbankingficticio.service.customeraccount.CustomerAccountService;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerAccountServiceConfig {
    @Autowired
    CustomerAccountService customerAccountService;

    @Bean
    public CustomerAccountServiceIF getCustomerAccountServiceIf() {
        return customerAccountService;
    }
}
