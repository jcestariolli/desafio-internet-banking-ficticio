package internetbankingficticio.mapper.customer;

import internetbankingficticio.test.AbstractTest;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static internetbankingficticio.test.utils.TestUtils.getDateNow;

@SpringBootTest
public class CustomerDaoToCustomerDtoMapperUnitTest extends AbstractTest {

    @Autowired
    CustomerDaoToCustomerDtoMapper mapper;

    @Test
    @DisplayName("Should map CustomerDao to CustomerDto")
    public void shouldMapCustomerDaoToCustomerDto() {
        CustomerDao customerDao = CustomerDao.builder().id(1).name("Customer Test").birthday(getDateNow()).build();
        CustomerDto customerDto = mapper.map(customerDao);
        Assertions.assertThat(customerDto.getId()).isEqualTo(customerDao.getId());
        Assertions.assertThat(customerDto.getName()).isEqualTo(customerDao.getName());
        Assertions.assertThat(customerDto.getBirthday()).isEqualTo(customerDao.getBirthday());
    }
}
