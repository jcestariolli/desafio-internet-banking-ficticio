package internetbankingficticio.service.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dao.customeraccount.CustomerAccountIdDaoKey;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountDtoToAccountDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customeraccount.CustomerAccountDaoToCustomerAccountDtoMapper;
import internetbankingficticio.repository.customeraccount.CustomerAccountRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.customer.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    CustomerAccountDaoToCustomerAccountDtoMapper customerAccountDaoToCustomerAccountDtoMapper;


    @Override
    public List<AccountDto> listAccountsByCustomerId(Long customerId) throws ResourceNotFoundException {
        return customerAccountRepository.findByCustomerId(customerDtoToCustomerDaoMapper.map(customerService.findCustomerById(customerId))).stream().map(customerAccountDao -> accountDaoToAccountDtoMapper.map(customerAccountDao.getAccountId())).toList();
    }

    @Override
    public List<CustomerDto> listCustomersByAccountId(String accountId) throws ResourceNotFoundException {
        return customerAccountRepository.findByAccountId(accountDtoToAccountDaoMapper.map(accountService.findAccountById(accountId))).stream().map(customerAccountDao -> customerDaoToCustomerDtoMapper.map(customerAccountDao.getCustomerId())).toList();
    }

    @Override
    public CustomerAccountDto createCustomerWithAccount(CustomerAccountCreateDto customerAccountCreateDto) {
        CustomerDto createdCustomerDto = customerService.createCustomer(customerAccountCreateDto.getCustomerDto());
        AccountDto createdAccountDto = accountService.createAccount(customerAccountCreateDto.getAccountDto());

        Optional<CustomerAccountDao> customerAccountDaoOpt = customerAccountRepository.findById(CustomerAccountIdDaoKey.builder().customerId(createdCustomerDto.getId()).accountId(createdAccountDto.getId()).build());
        if (customerAccountDaoOpt.isPresent()) {
            return customerAccountDaoToCustomerAccountDtoMapper.map(customerAccountDaoOpt.get());
        }
        CustomerAccountDao createdCustomerAccountDao = customerAccountRepository.save(CustomerAccountDao.builder().customerId(customerDtoToCustomerDaoMapper.map(createdCustomerDto)).accountId(accountDtoToAccountDaoMapper.map(createdAccountDto)).build());
        return customerAccountDaoToCustomerAccountDtoMapper.map(createdCustomerAccountDao);
    }

    public List<CustomerAccountDto> listAllCustomerAccounts() {
        return customerAccountRepository.findAll().stream().map(customerAccountDao -> customerAccountDaoToCustomerAccountDtoMapper.map(customerAccountDao)).toList();
    }
}
