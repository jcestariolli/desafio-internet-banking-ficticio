package internetbankingficticio.mapper.customeraccount;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static internetbankingficticio.utils.DateUtils.getDateNow;

@SpringBootTest
public class CustomerAccountDaoToCustomerAccountDtoMapperUnitTest extends AbstractTest {

    @Autowired
    CustomerAccountDaoToCustomerAccountDtoMapper mapper;
    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;

    @Test
    @DisplayName("Should map CustomerDao to CustomerDto")
    public void shouldMapCustomerDaoToCustomerDto() {
        CustomerDao customerDao = CustomerDao.builder().id(1).name("Customer Test").birthday(getDateNow()).build();
        AccountDao accountDao = AccountDao.builder().id("12345678").balance(new BigDecimal(100)).exclusivePlan(true).build();
        CustomerAccountDao customerAccountDao = CustomerAccountDao.builder().accountId(accountDao).customerId(customerDao).build();
        CustomerAccountDto customerAccountDto = mapper.map(customerAccountDao);
        Assertions.assertThat(customerAccountDto.getCustomerDto()).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
        Assertions.assertThat(customerAccountDto.getAccountDto()).isEqualTo(accountDaoToAccountDtoMapper.map(accountDao));
    }
}
