package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerAccountServiceIF {
    List<AccountDto> listAccountsByCustomerId(Long customerId) throws ResourceNotFoundException;
    List<CustomerDto> listCustomersByAccountId(String accountId) throws ResourceNotFoundException;
    CustomerAccountDto createCustomerWithAccount(CustomerAccountCreateDto customerAccountCreateDto);
    List<CustomerAccountDto> listAllCustomerAccounts();
}
