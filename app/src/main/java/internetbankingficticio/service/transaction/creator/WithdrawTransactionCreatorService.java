package internetbankingficticio.service.transaction.creator;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.exception.EntityNotFoundException;
import internetbankingficticio.exception.ValidationException;
import internetbankingficticio.mapper.transaction.TransactionCreateDtoToTransactionDaoMapper;
import internetbankingficticio.mapper.transaction.TransactionDaoToTransactionDtoMapper;
import internetbankingficticio.repository.transaction.TransactionRepository;
import internetbankingficticio.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class WithdrawTransactionCreatorService implements TransactionCreatorServiceIF {
    private static final TransactionCommand TRANSACTION_COMMAND = TransactionCommand.WITHDRAW;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;

    @Autowired
    TransactionDaoToTransactionDtoMapper transactionDaoToTransactionDtoMapper;
    @Autowired
    TransactionCreateDtoToTransactionDaoMapper transactionCreateDtoToTransactionDaoMapper;

    @Override
    public TransactionCommand getTransactionCommand() {
        return TRANSACTION_COMMAND;
    }

    @Override
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException, ValidationException {
        AccountDto accountDto = getAccountDtoFrom(transactionCreateDto);
        validate(transactionCreateDto, accountDto);
        accountService.updateAccountBalance(accountDto.getId(), accountDto.getBalance().subtract(transactionCreateDto.getAmmount()));
        return transactionDaoToTransactionDtoMapper.map(transactionRepository.save(transactionCreateDtoToTransactionDaoMapper.map(transactionCreateDto)));
    }

    private AccountDto getAccountDtoFrom(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException {
        Optional<AccountDto> accountDtoOpt = accountService.findAccountById(transactionCreateDto.getAccountId());
        if (accountDtoOpt.isEmpty()) throw new EntityNotFoundException();
        return accountDtoOpt.get();
    }

    public void validate(TransactionCreateDto transactionCreateDto, AccountDto accountDto) throws ValidationException {
        if (null == transactionCreateDto.getExecutedOn()) {
            transactionCreateDto.setExecutedOn(new Date());
        }
        if (transactionCreateDto.getAmmount().compareTo(accountDto.getBalance()) > 0) {
            throw new ValidationException();
        }
    }

}
