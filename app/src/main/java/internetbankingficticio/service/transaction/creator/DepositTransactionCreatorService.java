package internetbankingficticio.service.transaction.creator;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.exception.TransactionValidationException;
import internetbankingficticio.mapper.transaction.TransactionCreateDtoToTransactionDaoMapper;
import internetbankingficticio.mapper.transaction.TransactionDaoToTransactionDtoMapper;
import internetbankingficticio.repository.transaction.TransactionRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepositTransactionCreatorService implements TransactionCreatorServiceIF {

    private static final TransactionCommand TRANSACTION_COMMAND = TransactionCommand.DEPOSIT;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountServiceIF accountService;

    @Autowired
    TransactionDaoToTransactionDtoMapper transactionDaoToTransactionDtoMapper;
    @Autowired
    TransactionCreateDtoToTransactionDaoMapper transactionCreateDtoToTransactionDaoMapper;

    @Override
    public TransactionCommand getTransactionCreatorCommand() {
        return TRANSACTION_COMMAND;
    }

    @Override
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException, TransactionValidationException {
        AccountDto accountDto = accountService.findAccountById(transactionCreateDto.getAccountId());
        validate(transactionCreateDto);
        accountService.updateAccountBalance(accountDto.getId(), accountDto.getBalance().add(transactionCreateDto.getAmmount()));
        return transactionDaoToTransactionDtoMapper.map(transactionRepository.save(transactionCreateDtoToTransactionDaoMapper.map(transactionCreateDto)));
    }

    public void validate(TransactionCreateDto transactionCreateDto) throws TransactionValidationException {
        if (null == transactionCreateDto.getExecutedOn()) {
            transactionCreateDto.setExecutedOn(new Date());
        }
    }

}
