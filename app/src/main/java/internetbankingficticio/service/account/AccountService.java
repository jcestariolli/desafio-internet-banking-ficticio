package internetbankingficticio.service.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.mapper.account.AccountCreateDtoToAccountDaoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountUpdateDtoToAccountDaoMapper;
import internetbankingficticio.repository.account.AccountRepository;
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
    public AccountDto createAccount(AccountCreateDto accountDto) {
        return accountDaoToAccountDtoMapper.map(accountRepository.save(accountCreateDtoToAccountDaoMapper.map(accountDto)));

    }

    @Override
    public Optional<AccountDto> updateAccount(String accountId, AccountUpdateDto accountDto) {
        if (accountRepository.findById(accountId).isPresent()) {
            AccountDao accountToUpdate = accountUpdateDtoToAccountDaoMapper.map(accountDto);
            accountToUpdate.setId(accountId);
            return Optional.of(accountDaoToAccountDtoMapper.map(accountRepository.save(accountToUpdate)));
        }
        return Optional.empty();
    }
}
