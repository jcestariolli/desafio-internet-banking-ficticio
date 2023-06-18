package internetbankingficticio.mapper.transaction;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoToTransactionDaoMapper implements DtoToDaoMapper<TransactionDto, TransactionDao> {

    @Override
    public TransactionDao map(TransactionDto dto) {
        return TransactionDao.builder().id(dto.getId()).account(AccountDao.builder().id(dto.getAccountId()).build()).command(dto.getCommand()).ammount(dto.getAmmount()).executedOn(dto.getExecutedOn()).build();
    }
}
