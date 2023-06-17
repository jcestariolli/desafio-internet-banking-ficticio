package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerDaoMapper implements DtoToDaoMapper<CustomerDto, CustomerDao> {

    @Override
    public CustomerDao map(CustomerDto dto) {
        return CustomerDao.builder().id(dto.getId()).name(dto.getName()).birthday(dto.getBirthday()).build();
    }
}
