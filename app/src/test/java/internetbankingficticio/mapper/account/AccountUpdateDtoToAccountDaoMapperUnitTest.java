package internetbankingficticio.mapper.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountUpdateDtoToAccountDaoMapperUnitTest extends AbstractTest {

    @Autowired
    AccountUpdateDtoToAccountDaoMapper mapper;

    @Test
    @DisplayName("Should map AccountUpdateDto to AccountDao")
    void shouldMapAccountUpdateDtoToAccountDao() {
        AccountUpdateDto accountDto = AccountUpdateDto.builder().exclusivePlan(true).build();
        AccountDao accountDao = mapper.map(accountDto);
        Assertions.assertThat(accountDao.getExclusivePlan()).isEqualTo(accountDto.getExclusivePlan());
    }
}
