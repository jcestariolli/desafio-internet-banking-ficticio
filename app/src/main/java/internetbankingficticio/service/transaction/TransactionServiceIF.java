package internetbankingficticio.service.transaction;

import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionServiceIF {

    boolean existsById(Long transactionId);

    List<TransactionDto> listAllTransactions();

    Optional<TransactionDto> findTransactionById(Long transactionId);


    List<TransactionDto> findAllByExecutedOnBetween(Date executedOnStart, Date executedOnEnd);

    List<TransactionDto> findAllByAccountId(String accountId);

    List<TransactionDto> findAllByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd);

    TransactionDto createTransaction(TransactionCreateDto transactionDto);


}
