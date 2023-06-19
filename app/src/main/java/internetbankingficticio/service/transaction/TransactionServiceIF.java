package internetbankingficticio.service.transaction;

import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.ResourceNotFoundException;
import internetbankingficticio.exception.TransactionAmmountValidationException;

import java.util.Date;
import java.util.List;

public interface TransactionServiceIF {

    boolean existsById(Long transactionId);

    List<TransactionDto> listAllTransactions();

    TransactionDto findTransactionById(Long transactionId) throws ResourceNotFoundException;


    List<TransactionDto> listAllTransactionsByExecutedOnBetween(Date executedOnStart, Date executedOnEnd);

    List<TransactionDto> listAllTransactionsByAccountId(String accountId) throws ResourceNotFoundException;

    List<TransactionDto> listAllTransactionsByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd) throws ResourceNotFoundException;

    TransactionDto createTransaction(TransactionCreateDto transactionDto) throws TransactionAmmountValidationException, ResourceNotFoundException;


}
