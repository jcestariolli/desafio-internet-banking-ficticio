package internetbankingficticio.service.customer;

import internetbankingficticio.test.AbstractTest;
import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static internetbankingficticio.test.utils.customer.CustomerTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerServiceUnitTest extends AbstractTest {

    @Autowired
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;
    @Autowired
    CustomerCreateDtoToCustomerDaoMapper customerCreateDtoToCustomerDaoMapper;
    @Autowired
    CustomerUpdateDtoToCustomerDaoMapper customerUpdateDtoToCustomerDaoMapper;


    @Test
    @DisplayName("Should return Customer List when listAllCustomers()")
    public void shouldReturnCustomerList_whenListAllCustomers() {
        List<CustomerDao> customerDaoList = generateCustomerDaoListObject();
        mockRepositoryFindAllWithCustomerList(customerRepository, customerDaoList);

        List<CustomerDto> expectedCustomerDtoList = customerDaoList.stream().map(customerDao -> customerDaoToCustomerDtoMapper.map(customerDao)).collect(Collectors.toList());
        List<CustomerDto> returnedCustomerDtoList = customerService.listAllCustomers();

        assertThat(returnedCustomerDtoList).isNotEmpty();
        assertThat(returnedCustomerDtoList).hasSize(expectedCustomerDtoList.size());
        assertThat(returnedCustomerDtoList).containsExactlyElementsOf(expectedCustomerDtoList);
    }

    @Test
    @DisplayName("Should return Customer when findCustomerById() finds a Customer")
    public void shouldReturnCustomer_whenFindCustomerByIdFindsCustomer() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, customerDao);

        Optional<CustomerDto> returnedCustomerDtoOpt = customerService.findCustomerById(customerTestId);

        assertThat(returnedCustomerDtoOpt).isPresent();
        assertThat(returnedCustomerDtoOpt.get()).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
    }

    @Test
    @DisplayName("Should return Empty when findCustomerById() does not find a Customer")
    public void shouldReturnEmpty_whenFindCustomerByIdDoesNotFindCustomer() {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);

        Optional<CustomerDto> returnedCustomerDtoOpt = customerService.findCustomerById(customerTestId);

        assertThat(returnedCustomerDtoOpt).isEmpty();
    }

    @Test
    @DisplayName("Should return Customer when createCustomer()")
    public void shouldReturnCustomer_whenCreateCustomer() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockRepositorySaveWithCustomer(customerRepository, customerDao);

        CustomerDto returnedCustomerDto = customerService.createCustomer(CustomerCreateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());

        assertThat(returnedCustomerDto).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
    }

    @Test
    @DisplayName("Should return Customer when updateCustomer() finds the Customer to update")
    public void shouldReturnCustomer_whenUpdateCustomerFindsTheCustomerToUpdate() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, customerDao);
        mockRepositorySaveWithCustomer(customerRepository, customerDao);

        Optional<CustomerDto> returnedCustomerDtoOpt = customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());

        assertThat(returnedCustomerDtoOpt).isPresent();
        assertThat(returnedCustomerDtoOpt.get()).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
    }

    @Test
    @DisplayName("Should return Empty when updateCustomer() does not find the Customer to update")
    public void shouldReturnCustomer_whenUpdateCustomerDoesNotFindTheCustomerToUpdate() {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);

        Optional<CustomerDto> returnedCustomerDtoOpt = customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().build());

        assertThat(returnedCustomerDtoOpt).isEmpty();
    }


}
