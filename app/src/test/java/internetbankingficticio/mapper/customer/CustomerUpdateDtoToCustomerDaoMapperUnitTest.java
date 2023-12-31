package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static internetbankingficticio.utils.DateUtils.getDateNow;

@SpringBootTest
class CustomerUpdateDtoToCustomerDaoMapperUnitTest extends AbstractTest {

    @Autowired
    CustomerUpdateDtoToCustomerDaoMapper mapper;

    @Test
    @DisplayName("Should map CustomerUpdateDto to CustomerDao")
    void shouldMapCustomerUpdateDtoToCustomerDao() {
        CustomerUpdateDto customerDto = CustomerUpdateDto.builder().name("Customer Test").birthday(getDateNow()).build();
        CustomerDao customerDao = mapper.map(customerDto);
        Assertions.assertThat(customerDao.getName()).isEqualTo(customerDto.getName());
        Assertions.assertThat(customerDao.getBirthday()).isEqualTo(customerDto.getBirthday());
    }
}
