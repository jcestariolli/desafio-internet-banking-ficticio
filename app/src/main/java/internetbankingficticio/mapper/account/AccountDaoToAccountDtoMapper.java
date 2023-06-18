package internetbankingficticio.mapper.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.mapper.DaoToDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountDaoToAccountDtoMapper implements DaoToDtoMapper<AccountDao, AccountDto> {
    @Override
    public AccountDto map(AccountDao dao) {
        return AccountDto.builder().id(dao.getId()).balance(dao.getBalance()).exclusivePlan(dao.getExclusivePlan()).build();
    }
}
