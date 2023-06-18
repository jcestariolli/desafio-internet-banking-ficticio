package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;

import java.util.List;
import java.util.Optional;

public interface CustomerAccountServiceIF {
    Optional<List<AccountDto>> findAccountsByCustomerId(Long customerId);
    Optional<List<CustomerDto>> findCustomersByAccountId(String accountId);
    CustomerAccountDto createCustomerWithAccount(CustomerAccountCreateDto customerAccountCreateDto);
}
