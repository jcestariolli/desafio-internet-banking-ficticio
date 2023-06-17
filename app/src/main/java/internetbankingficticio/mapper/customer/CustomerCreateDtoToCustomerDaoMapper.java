package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreateDtoToCustomerDaoMapper implements DtoToDaoMapper<CustomerCreateDto, CustomerDao> {

    @Override
    public CustomerDao map(CustomerCreateDto dto) {
        return CustomerDao.builder().name(dto.getName()).birthday(dto.getBirthday()).build();
    }
}
