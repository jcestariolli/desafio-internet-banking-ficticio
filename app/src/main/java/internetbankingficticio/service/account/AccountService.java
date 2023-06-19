package internetbankingficticio.service.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.exception.AccountResourceNotFoundException;
import internetbankingficticio.exception.ResourceNotFoundException;
import internetbankingficticio.mapper.account.AccountCreateDtoToAccountDaoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountUpdateDtoToAccountDaoMapper;
import internetbankingficticio.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements AccountServiceIF {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapper;
    @Autowired
    AccountCreateDtoToAccountDaoMapper accountCreateDtoToAccountDaoMapper;
    @Autowired
    AccountUpdateDtoToAccountDaoMapper accountUpdateDtoToAccountDaoMapper;

    @Override
    public List<AccountDto> listAllAccounts() {
        return accountRepository.findAll().stream().map(accountDao -> accountDaoToAccountDtoMapper.map(accountDao)).collect(Collectors.toList());
    }

    @Override
    public AccountDto findAccountById(String accountId) throws AccountResourceNotFoundException {
        Optional<AccountDao> accountDaoOpt = accountRepository.findById(accountId);
        if (accountDaoOpt.isEmpty()) throw new AccountResourceNotFoundException(accountId);
        return accountDaoToAccountDtoMapper.map(accountDaoOpt.get());
    }

    @Override
    public boolean existsById(String accountId) {
        return accountRepository.existsById(accountId);
    }

    @Override
    public AccountDto createAccount(AccountCreateDto accountDto) {
        return accountDaoToAccountDtoMapper.map(accountRepository.save(accountCreateDtoToAccountDaoMapper.map(accountDto)));

    }

    @Override
    public AccountDto updateAccount(String accountId, AccountUpdateDto accountDto) throws AccountResourceNotFoundException {
        Optional<AccountDao> accountDao = accountRepository.findById(accountId);
        if (accountDao.isEmpty()) throw new AccountResourceNotFoundException(accountId);
        AccountDao accountToUpdate = accountUpdateDtoToAccountDaoMapper.map(accountDto);
        accountToUpdate.setId(accountId);
        accountToUpdate.setBalance(accountDao.get().getBalance());
        accountToUpdate.setExclusivePlan(accountToUpdate.getExclusivePlan());
        return accountDaoToAccountDtoMapper.map(accountRepository.save(accountToUpdate));
    }

    @Override
    public AccountDto updateAccountBalance(String accountId, BigDecimal ammout) throws ResourceNotFoundException {
        Optional<AccountDao> accountDao = accountRepository.findById(accountId);
        if (accountDao.isEmpty()) throw new AccountResourceNotFoundException(accountId);
        AccountDao accountToUpdate = accountDao.get();
        accountToUpdate.setBalance(ammout);
        return accountDaoToAccountDtoMapper.map(accountRepository.save(accountToUpdate));

    }

}
