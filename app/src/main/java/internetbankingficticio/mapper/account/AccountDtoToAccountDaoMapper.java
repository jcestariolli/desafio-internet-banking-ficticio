package internetbankingficticio.mapper.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoToAccountDaoMapper implements DtoToDaoMapper<AccountDto, AccountDao> {

    @Override
    public AccountDao map(AccountDto dto) {
        return AccountDao.builder().id(dto.getId()).balance(dto.getBalance()).exclusivePlan(dto.getExclusivePlan()).build();
    }
}
