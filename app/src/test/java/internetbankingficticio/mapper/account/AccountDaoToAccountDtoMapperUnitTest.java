package internetbankingficticio.mapper.account;


import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class AccountDaoToAccountDtoMapperUnitTest extends AbstractTest {

    @Autowired
    AccountDaoToAccountDtoMapper mapper;

    @Test
    @DisplayName("Should map AccountDao to AccountDto")
    void shouldMapAccountDaoToAccountDto() {
        AccountDao accountDao = AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build();
        AccountDto accountDto = mapper.map(accountDao);
        Assertions.assertThat(accountDto.getId()).isEqualTo(accountDao.getId());
        Assertions.assertThat(accountDto.getBalance()).isEqualTo(accountDao.getBalance());
        Assertions.assertThat(accountDto.isExclusivePlan()).isEqualTo(accountDao.getExclusivePlan());
    }
}
