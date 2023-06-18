package internetbankingficticio.service.transaction;

import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.exception.TransactionAmmountValidationException;

import java.util.Date;
import java.util.List;

public interface TransactionServiceIF {

    boolean existsById(Long transactionId);

    List<TransactionDto> listAllTransactions();

    TransactionDto findTransactionById(Long transactionId) throws EntityNotFoundException;


    List<TransactionDto> listAllTransactionsByExecutedOnBetween(Date executedOnStart, Date executedOnEnd);

    List<TransactionDto> listAllTransactionsByAccountId(String accountId) throws EntityNotFoundException;

    List<TransactionDto> listAllTransactionsByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd) throws EntityNotFoundException;

    TransactionDto createTransaction(TransactionCreateDto transactionDto) throws TransactionAmmountValidationException, EntityNotFoundException;


}
