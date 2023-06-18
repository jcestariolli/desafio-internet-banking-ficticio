package internetbankingficticio.service.transaction;

import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.TransactionValidationException;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.exception.entity.TransactionEntityNotFoundException;
import internetbankingficticio.mapper.transaction.TransactionDaoToTransactionDtoMapper;
import internetbankingficticio.repository.transaction.TransactionRepository;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.transaction.creator.TransactionCreatorServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements TransactionServiceIF {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountServiceIF accountService;
    @Autowired
    TransactionDaoToTransactionDtoMapper transactionDaoToTransactionDtoMapper;

    @Autowired
    TransactionCreatorServiceFactory transactionCreatorServiceFactory;

    @Override
    public List<TransactionDto> listAllTransactions() {
        return transactionRepository.findAll().stream().map(transactionDao -> transactionDaoToTransactionDtoMapper.map(transactionDao)).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findTransactionById(Long transactionId) throws TransactionEntityNotFoundException {
        Optional<TransactionDao> transactionDaoOpt = transactionRepository.findById(transactionId);
        if (transactionDaoOpt.isEmpty()) throw new TransactionEntityNotFoundException(transactionId.toString());
        return transactionDaoToTransactionDtoMapper.map(transactionDaoOpt.get());
    }

    @Override
    public boolean existsById(Long transactionId) {
        return transactionRepository.existsById(transactionId);
    }

    @Override
    public List<TransactionDto> listAllTransactionsByExecutedOnBetween(Date executedOnStart, Date executedOnEnd) {
        return transactionRepository.findAllByExecutedOnBetween(executedOnStart, executedOnEnd).stream().map(transactionDao -> transactionDaoToTransactionDtoMapper.map(transactionDao)).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> listAllTransactionsByAccountId(String accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(transactionDao -> transactionDaoToTransactionDtoMapper.map(transactionDao)).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> listAllTransactionsByAccountIdAndExecutedOnBetween(String accountId, Date executedOnStart, Date executedOnEnd) {
        return transactionRepository.findAllByAccountIdAndExecutedOnBetween(accountId, executedOnStart, executedOnEnd).stream().map(transactionDao -> transactionDaoToTransactionDtoMapper.map(transactionDao)).collect(Collectors.toList());
    }

    @Override
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) throws TransactionValidationException, EntityNotFoundException {
        return transactionCreatorServiceFactory.provide(transactionCreateDto.getCommand()).createTransaction(transactionCreateDto);
    }


}
