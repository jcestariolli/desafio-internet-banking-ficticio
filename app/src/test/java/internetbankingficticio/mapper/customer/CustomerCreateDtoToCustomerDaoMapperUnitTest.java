package internetbankingficticio.mapper.customer;

import internetbankingficticio.test.AbstractTest;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static internetbankingficticio.test.utils.TestUtils.getDateNow;

@SpringBootTest
public class CustomerCreateDtoToCustomerDaoMapperUnitTest extends AbstractTest {

    @Autowired
    CustomerCreateDtoToCustomerDaoMapper mapper;

    @Test
    @DisplayName("Should map CustomerCreateDto to CustomerDao")
    public void shouldMapCustomerCreateDtoToCustomerDao() {
        CustomerCreateDto customerDto = CustomerCreateDto.builder().name("Customer Test").birthday(getDateNow()).build();
        CustomerDao customerDao = mapper.map(customerDto);
        Assertions.assertThat(customerDao.getName()).isEqualTo(customerDto.getName());
        Assertions.assertThat(customerDao.getBirthday()).isEqualTo(customerDto.getBirthday());
    }
}
