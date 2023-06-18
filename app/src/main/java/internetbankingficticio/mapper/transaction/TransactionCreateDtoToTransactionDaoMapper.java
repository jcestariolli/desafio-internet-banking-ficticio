package internetbankingficticio.mapper.transaction;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dao.transaction.TransactionDao;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionCreateDtoToTransactionDaoMapper implements DtoToDaoMapper<TransactionCreateDto, TransactionDao> {

    @Override
    public TransactionDao map(TransactionCreateDto dto) {
        return TransactionDao.builder().account(AccountDao.builder().id(dto.getAccountId()).build()).command(dto.getCommand()).ammount(dto.getAmmount()).executedOn(dto.getExecutedOn()).build();
    }
}
