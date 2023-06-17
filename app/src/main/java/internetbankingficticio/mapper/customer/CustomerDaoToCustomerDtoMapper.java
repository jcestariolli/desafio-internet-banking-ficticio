package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.mapper.DaoToDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoToCustomerDtoMapper implements DaoToDtoMapper<CustomerDao, CustomerDto> {
    @Override
    public CustomerDto map(CustomerDao dao) {
        return CustomerDto.builder().id(dao.getId()).name(dao.getName()).birthday(dao.getBirthday()).build();
    }
}
