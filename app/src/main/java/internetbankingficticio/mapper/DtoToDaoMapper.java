package internetbankingficticio.mapper;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import internetbankingficticio.dto.AbstractInternetBankingDto;

public interface DtoToDaoMapper<T extends AbstractInternetBankingDto, A extends AbstractInternetBankingDao> {
    A map(T dto);
}
