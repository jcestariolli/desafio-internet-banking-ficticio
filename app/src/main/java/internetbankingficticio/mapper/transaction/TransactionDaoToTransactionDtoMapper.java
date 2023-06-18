package internetbankingficticio.mapper.transaction;

import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.mapper.DaoToDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDaoToTransactionDtoMapper implements DaoToDtoMapper<TransactionDao, TransactionDto> {
    @Override
    public TransactionDto map(TransactionDao dao) {
        return TransactionDto.builder().id(dao.getId()).accountId(dao.getAccount().getId()).command(dao.getCommand()).ammount(dao.getAmmount()).executedOn(dao.getExecutedOn()).build();
    }
}
