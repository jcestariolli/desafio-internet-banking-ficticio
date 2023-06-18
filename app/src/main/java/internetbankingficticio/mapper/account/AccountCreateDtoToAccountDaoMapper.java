package internetbankingficticio.mapper.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountCreateDtoToAccountDaoMapper implements DtoToDaoMapper<AccountCreateDto, AccountDao> {

    @Override
    public AccountDao map(AccountCreateDto dto) {
        return AccountDao.builder().id(dto.getId()).balance(dto.getBalance()).exclusivePlan(dto.getExclusivePlan()).build();
    }
}
