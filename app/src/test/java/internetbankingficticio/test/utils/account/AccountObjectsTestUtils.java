package internetbankingficticio.test.utils.account;

import internetbankingficticio.dao.account.AccountDao;
import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;

import java.math.BigDecimal;
import java.util.List;

public class AccountObjectsTestUtils {


    public static List<AccountDao> generateAccountDaoListObject() {
        return List.of(generateAccountDaoObject("12345678", new BigDecimal(100), true), generateAccountDaoObject("0101010101", new BigDecimal("1.10"), false), generateAccountDaoObject("99999999", new BigDecimal(200), true));
    }

    public static AccountDao generateAccountDaoObject(String id, BigDecimal balance, boolean exclusivePlan) {
        return AccountDao.builder().id(id).balance(balance).exclusivePlan(exclusivePlan).build();
    }

    public static List<AccountDto> generateAccountDtoListObject() {
        return List.of(generateAccountDtoObject("12345678", new BigDecimal(100), true), generateAccountDtoObject("0101010101", new BigDecimal("1.10"), false), generateAccountDtoObject("99999999", new BigDecimal(200), true));
    }

    public static AccountDto generateAccountDtoObject(String id, BigDecimal balance, boolean exclusivePlan) {
        return AccountDto.builder().id(id).balance(balance).exclusivePlan(exclusivePlan).build();
    }

    public static AccountCreateDto generateAccountCreateDtoObject(String id, BigDecimal balance, boolean exclusivePlan) {
        return AccountCreateDto.builder().id(id).balance(balance).exclusivePlan(exclusivePlan).build();
    }

    public static AccountUpdateDto generateAccountUpdateDtoObject(boolean exclusivePlan) {
        return AccountUpdateDto.builder().exclusivePlan(exclusivePlan).build();
    }
}
