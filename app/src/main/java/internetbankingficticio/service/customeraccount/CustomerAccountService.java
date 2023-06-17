package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountIdDaoKey;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountDtoToAccountDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customeraccount.CustomerAccountRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.customer.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerAccountService implements CustomerAccountServiceIF {

    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    AccountServiceIF accountService;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    AccountDtoToAccountDaoMapper accountDtoToAccountDaoMapper;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;
    @Autowired
    CustomerDtoToCustomerDaoMapper customerDtoToCustomerDaoMapper;


    @Override
    public Optional<List<AccountDto>> findAccountsByCustomerId(Long customerId) {
        Optional<CustomerDto> customerDto = customerService.findCustomerById(customerId);
        if (customerDto.isEmpty())
            return Optional.empty();
        List<CustomerAccountDao> customerAccountDaoList = customerAccountRepository.findByCustomerId(customerDtoToCustomerDaoMapper.map(customerDto.get()));
        return Optional.of(customerAccountDaoList.stream().map(customerAccountDao -> accountDaoToAccountDtoMapper.map(customerAccountDao.getAccountId())).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<CustomerDto>> findCustomersByAccountId(String accountId) {
        Optional<AccountDto> accountDto = accountService.findAccountById(accountId);
        if (accountDto.isEmpty())
            return Optional.empty();
        List<CustomerAccountDao> customerAccountDaoList = customerAccountRepository.findByAccountId(accountDtoToAccountDaoMapper.map(accountDto.get()));
        return Optional.of(customerAccountDaoList.stream().map(customerAccountDao -> customerDaoToCustomerDtoMapper.map(customerAccountDao.getCustomerId())).collect(Collectors.toList()));
    }

    @Override
    public CustomerAccountDto createCustomerWithAccount(CustomerAccountCreateDto customerAccountCreateDto) {
        CustomerDto createdCustomerDto = customerService.createCustomer(customerAccountCreateDto.getCustomerDto());
        AccountDto createdAccountDto = accountService.createAccount(customerAccountCreateDto.getAccountDto());


        Optional<CustomerAccountDao> customerAccountDaoOpt = customerAccountRepository.findById(CustomerAccountIdDaoKey.builder().customerId(createdCustomerDto.getId()).accountId(createdAccountDto.getId()).build());
        if (customerAccountDaoOpt.isPresent()){

        }
        CustomerAccountDao createdCustomerAccountDao = CustomerAccountDao.builder().customerId(customerDtoToCustomerDaoMapper.map(createdCustomerDto)).accountId(accountDtoToAccountDaoMapper.map(createdAccountDto)).build();
        return null;
    }
}
