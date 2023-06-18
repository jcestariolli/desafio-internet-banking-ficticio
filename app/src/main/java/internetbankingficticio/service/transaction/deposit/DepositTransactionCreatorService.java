package internetbankingficticio.service.transaction.deposit;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.exception.TransactionAmmountValidationException;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.mapper.transaction.TransactionCreateDtoToTransactionDaoMapper;
import internetbankingficticio.mapper.transaction.TransactionDaoToTransactionDtoMapper;
import internetbankingficticio.repository.transaction.TransactionRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.transaction.TransactionCreatorServiceIF;
import internetbankingficticio.utils.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException, TransactionAmmountValidationException {
        AccountDto accountDto = accountService.findAccountById(transactionCreateDto.getAccountId());
        validateTransaction(accountDto.getBalance(), transactionCreateDto.getAmount());
        accountService.updateAccountBalance(accountDto.getId(), accountDto.getBalance().add(transactionCreateDto.getAmount()));
        return transactionDaoToTransactionDtoMapper.map(transactionRepository.save(transactionCreateDtoToTransactionDaoMapper.map(transactionCreateDto)));
    }

    private void validateTransaction(BigDecimal accountBalance, BigDecimal transactionAmount) throws TransactionAmmountValidationException {
        if (BigDecimalUtils.lessThanOrEqualsToZero(transactionAmount)) {
            throw new TransactionAmmountValidationException("Valor da transação não pode ser igual ou menor do que zero");
        }
    }
}
