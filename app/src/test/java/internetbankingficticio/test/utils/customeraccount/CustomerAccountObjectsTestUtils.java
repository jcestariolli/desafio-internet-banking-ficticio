package internetbankingficticio.test.utils.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountCreateDtoObject;
import static internetbankingficticio.test.utils.account.AccountObjectsTestUtils.generateAccountDaoObject;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerCreateDtoObject;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDaoObject;

public class CustomerAccountObjectsTestUtils {

    public static CustomerAccountDao generateCustomerAccountDaoObject() {
        return CustomerAccountDao.builder().accountId(generateAccountDaoObject("12345678", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true)).customerId(generateCustomerDaoObject(1L, "Customer Test 1")).build();
    }

    public static CustomerAccountCreateDto generateCustomerAccountCreateDtoObject() {
        return CustomerAccountCreateDto.builder().accountDto(generateAccountCreateDtoObject("12345678", BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), true)).customerDto(generateCustomerCreateDtoObject("Customer Test")).build();
    }
}
