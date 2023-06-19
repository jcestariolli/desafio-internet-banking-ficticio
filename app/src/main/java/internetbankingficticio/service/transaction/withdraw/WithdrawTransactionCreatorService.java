package internetbankingficticio.service.transaction.withdraw;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.exception.validation.TransactionAmmountValidationException;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;
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
public class WithdrawTransactionCreatorService implements TransactionCreatorServiceIF {
    private static final TransactionCommand TRANSACTION_COMMAND = TransactionCommand.WITHDRAW;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountServiceIF accountService;

    @Autowired
    WithdrawTransactionCalculatorIF withdrawTransactionAmmountCalculator;

    @Autowired
    TransactionDaoToTransactionDtoMapper transactionDaoToTransactionDtoMapper;
    @Autowired
    TransactionCreateDtoToTransactionDaoMapper transactionCreateDtoToTransactionDaoMapper;

    @Override
    public TransactionCommand getTransactionCreatorCommand() {
        return TRANSACTION_COMMAND;
    }

    @Override
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws ResourceNotFoundException, TransactionAmmountValidationException {
        AccountDto accountDto = accountService.findAccountById(transactionCreateDto.getAccountId());
        transactionCreateDto.setAmount(withdrawTransactionAmmountCalculator.applyBusinessRuleInTransactionAmount(transactionCreateDto.getAmount(), accountDto));
        validateTransaction(accountDto.getBalance(), transactionCreateDto.getAmount());
        accountService.updateAccountBalance(accountDto.getId(), withdrawTransactionAmmountCalculator.calculateAccountBalanceAfterTransaction(accountDto.getBalance(), transactionCreateDto.getAmount()));
        return transactionDaoToTransactionDtoMapper.map(transactionRepository.save(transactionCreateDtoToTransactionDaoMapper.map(transactionCreateDto)));
    }

    private void validateTransaction(BigDecimal accountBalance, BigDecimal transactionAmount) throws TransactionAmmountValidationException {
        if (BigDecimalUtils.lessThanOrEqualsToZero(transactionAmount)) {
            throw new TransactionAmmountValidationException("Valor da transação não pode ser igual ou menor do que zero");
        }
        if (BigDecimalUtils.greaterThan(transactionAmount, accountBalance)) {
            throw new TransactionAmmountValidationException("Valor de saque é maior do que valor do saldo da conta");
        }
    }

}
