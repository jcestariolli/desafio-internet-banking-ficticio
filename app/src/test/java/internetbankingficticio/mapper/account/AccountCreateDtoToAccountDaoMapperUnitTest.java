package internetbankingficticio.mapper.account;


import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class AccountCreateDtoToAccountDaoMapperUnitTest extends AbstractTest {

    @Autowired
    AccountCreateDtoToAccountDaoMapper mapper;

    @Test
    @DisplayName("Should map AccountCreateDto to AccountDao")
    public void shouldMapAccountCreateDtoToAccountDao() {
        AccountCreateDto accountDto = AccountCreateDto.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build();
        AccountDao accountDao = mapper.map(accountDto);
        Assertions.assertThat(accountDao.getId()).isEqualTo(accountDto.getId());
        Assertions.assertThat(accountDao.getBalance()).isEqualTo(accountDto.getBalance());
        Assertions.assertThat(accountDao.getExclusivePlan()).isEqualTo(accountDto.getExclusivePlan());
    }
}
