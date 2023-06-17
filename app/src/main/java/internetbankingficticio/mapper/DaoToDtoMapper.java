package internetbankingficticio.mapper;

import internetbankingficticio.dao.AbstractInternetBankingDao;
import internetbankingficticio.dto.AbstractInternetBankingDto;

public interface DaoToDtoMapper<A extends AbstractInternetBankingDao, T extends AbstractInternetBankingDto> {
    T map(A dao);
}
