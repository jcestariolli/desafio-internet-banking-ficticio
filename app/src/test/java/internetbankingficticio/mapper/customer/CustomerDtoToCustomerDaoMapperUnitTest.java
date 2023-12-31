package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.test.AbstractTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static internetbankingficticio.utils.DateUtils.getDateNow;

@SpringBootTest
class CustomerDtoToCustomerDaoMapperUnitTest extends AbstractTest {

    @Autowired
    CustomerDtoToCustomerDaoMapper mapper;

    @Test
    @DisplayName("Should map CustomerDto to CustomerDao")
    void shouldMapCustomerDtoToCustomerDao() {
        CustomerDto customerDto = CustomerDto.builder().id(1).name("Customer Test").birthday(getDateNow()).build();
        CustomerDao customerDao = mapper.map(customerDto);
        Assertions.assertThat(customerDao.getId()).isEqualTo(customerDto.getId());
        Assertions.assertThat(customerDao.getName()).isEqualTo(customerDto.getName());
        Assertions.assertThat(customerDao.getBirthday()).isEqualTo(customerDto.getBirthday());
    }
}
