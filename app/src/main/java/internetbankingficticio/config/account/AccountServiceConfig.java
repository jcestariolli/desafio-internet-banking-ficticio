package internetbankingficticio.config.account;

import internetbankingficticio.service.account.AccountService;
import internetbankingficticio.service.account.AccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountServiceConfig {
    @Autowired
    AccountService accountService;

    @Bean
    public AccountServiceIF getAccountServiceIf() {
        return accountService;
    }
}
