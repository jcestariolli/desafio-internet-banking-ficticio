package internetbankingficticio.test.utils.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.mapper.account.AccountCreateDtoToAccountDaoMapper;
import internetbankingficticio.mapper.account.AccountDaoToAccountDtoMapper;
import internetbankingficticio.mapper.account.AccountUpdateDtoToAccountDaoMapper;
import internetbankingficticio.repository.account.AccountRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountRepositoryMockTestUtils {

    public static void mockRepositoryFindAllWithAccountList(AccountRepository accountRepositoryMock, List<AccountDao> accountDaoList) {
        when(accountRepositoryMock.findAll()).thenReturn(accountDaoList);
    }

    public static void mockRepositoryFindByIdWithAccount(AccountRepository accountRepositoryMock, String idToMock, AccountDao accountDao) {
        when(accountRepositoryMock.findById(idToMock)).thenReturn(Optional.of(accountDao));
    }

    public static void mockRepositoryFindByIdWithEmptyResult(AccountRepository accountRepositoryMock, String idToMock) {
        when(accountRepositoryMock.findById(idToMock)).thenReturn(Optional.empty());
    }

    public static void mockRepositorySaveWithAccount(AccountRepository accountRepositoryMock, AccountDao accountDao) {
        when(accountRepositoryMock.save(any(AccountDao.class))).thenReturn(accountDao);
    }

    public static void mockAccountDaoToAccountDtoMapperMap(AccountDaoToAccountDtoMapper accountDaoToAccountDtoMapperMock, AccountDto accountDto) {
        when(accountDaoToAccountDtoMapperMock.map(any())).thenReturn(accountDto);
    }

    public static void mockAccountCreateDtoToAccountDaoMapperMap(AccountCreateDtoToAccountDaoMapper accountCreateDtoToAccountDaoMapperMock, AccountDao accountDao) {
        when(accountCreateDtoToAccountDaoMapperMock.map(any(AccountCreateDto.class))).thenReturn(accountDao);
    }

    public static void mockAccountUpdateDtoToAccountDaoMapperMap(AccountUpdateDtoToAccountDaoMapper accountUpdateDtoToAccountDaoMapperMock, AccountDao accountDao) {
        when(accountUpdateDtoToAccountDaoMapperMock.map(any(AccountUpdateDto.class))).thenReturn(accountDao);
    }

}
