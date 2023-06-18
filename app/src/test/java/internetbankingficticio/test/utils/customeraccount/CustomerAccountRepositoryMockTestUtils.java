package internetbankingficticio.test.utils.customeraccount;

import internetbankingficticio.dao.customeraccount.CustomerAccountDao;
import internetbankingficticio.repository.customeraccount.CustomerAccountRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerAccountRepositoryMockTestUtils {
    public static void mockRepositoryFindByCustomerIdWithCustomerAccountList(CustomerAccountRepository customerAccountRepository, List<CustomerAccountDao> customerAccountDaoList) {
        when(customerAccountRepository.findByCustomerId(any())).thenReturn(customerAccountDaoList);
    }
    public static void mockRepositoryFindByAccountIdWithCustomerAccountList(CustomerAccountRepository customerAccountRepository, List<CustomerAccountDao> customerAccountDaoList) {
        when(customerAccountRepository.findByAccountId(any())).thenReturn(customerAccountDaoList);
    }

    public static void mockRepositoryFindByIdWithCustomerAccount(CustomerAccountRepository customerAccountRepository, CustomerAccountDao customerAccountDao) {
        when(customerAccountRepository.findById(any())).thenReturn(Optional.of(customerAccountDao));
    }

    public static void mockRepositoryFindByIdWithEmptyResult(CustomerAccountRepository customerAccountRepository) {
        when(customerAccountRepository.findById(any())).thenReturn(Optional.empty());
    }

    public static void mockRepositoryFindAllWithCustomerAccountList(CustomerAccountRepository customerAccountRepository, List<CustomerAccountDao> customerAccountDaoList) {
        when(customerAccountRepository.findAll()).thenReturn(customerAccountDaoList);
    }

    public static void mockRepositorySaveWithCustomerAccount(CustomerAccountRepository customerAccountRepository, CustomerAccountDao customerAccountDao) {
        when(customerAccountRepository.save(any())).thenReturn(customerAccountDao);
    }
}
