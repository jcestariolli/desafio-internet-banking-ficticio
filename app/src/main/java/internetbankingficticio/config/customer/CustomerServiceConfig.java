package internetbankingficticio.config.customer;

import internetbankingficticio.service.customer.CustomerService;
import internetbankingficticio.service.customer.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerServiceConfig {
    @Autowired
    CustomerService customerService;

    @Bean
    public CustomerServiceIF getCustomerServiceIf() {
        return customerService;
    }
}
