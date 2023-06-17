package internetbankingficticio.mapper.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.mapper.DaoToDtoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountDaoToCustomerAccountDtoMapper implements DaoToDtoMapper<CustomerAccountDao, CustomerAccountDto> {
    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;

    @Override
    public CustomerAccountDto map(CustomerAccountDao dao) {
        return CustomerAccountDto.builder()
                                 .customerDto(customerDaoToCustomerDtoMapper.map(dao.getCustomerId()))
                                 .accountDto(accountDaoToAccountDtoMapper.map(dao.getAccountId()))
                                 .build();
    }
}
