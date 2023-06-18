package internetbankingficticio.service.transaction.creator;

import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.enums.transaction.TransactionCommand;
import internetbankingficticio.exception.EntityNotFoundException;
import internetbankingficticio.exception.ValidationException;

public interface TransactionCreatorServiceIF {
    TransactionCommand getTransactionCommand();
    TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws EntityNotFoundException, ValidationException;
}
