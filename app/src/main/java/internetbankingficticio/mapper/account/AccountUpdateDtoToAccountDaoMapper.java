package internetbankingficticio.mapper.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.mapper.DtoToDaoMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountUpdateDtoToAccountDaoMapper implements DtoToDaoMapper<AccountUpdateDto, AccountDao> {

    @Override
    public AccountDao map(AccountUpdateDto dto) {
        return AccountDao.builder().exclusivePlan(dto.getExclusivePlan()).build();
    }
}
