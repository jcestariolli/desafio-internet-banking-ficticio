package internetbankingficticio.service.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.mapper.account.AccountCreateDtoToAccountDaoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountUpdateDtoToAccountDaoMapper;
import internetbankingficticio.repository.account.AccountRepository;
import internetbankingficticio.service.transaction.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements AccountServiceIF {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionServiceIF transactionService;
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
    public Optional<AccountDto> findAccountById(String accountId) {
        Optional<AccountDao> accountDaoOpt = accountRepository.findById(accountId);
        return accountDaoOpt.isEmpty() ? Optional.empty() : Optional.of(accountDaoToAccountDtoMapper.map(accountDaoOpt.get()));
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
    public Optional<AccountDto> updateAccount(String accountId, AccountUpdateDto accountDto) {
        Optional<AccountDao> accountDao = accountRepository.findById(accountId);
        if (accountDao.isPresent()) {
            AccountDao accountToUpdate = accountUpdateDtoToAccountDaoMapper.map(accountDto);
            accountToUpdate.setId(accountId);
            accountToUpdate.setBalance(accountDao.get().getBalance());
            accountToUpdate.setExclusivePlan(accountToUpdate.getExclusivePlan());
            return Optional.of(accountDaoToAccountDtoMapper.map(accountRepository.save(accountToUpdate)));
        }
        return Optional.empty();
    }

    @Override
    public List<TransactionDto> findAllTransactionsByAccountId(String accountId) {
        return transactionService.findAllByAccountId(accountId);
    }
}
