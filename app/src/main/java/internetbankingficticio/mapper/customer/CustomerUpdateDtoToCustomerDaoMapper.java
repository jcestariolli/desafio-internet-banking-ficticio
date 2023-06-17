package internetbankingficticio.mapper.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerUpdateDtoToCustomerDaoMapper implements DtoToDaoMapper<CustomerUpdateDto, CustomerDao> {

    @Override
    public CustomerDao map(CustomerUpdateDto dto) {
        return CustomerDao.builder().name(dto.getName()).birthday(dto.getBirthday()).build();
    }
}
