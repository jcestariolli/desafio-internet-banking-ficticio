package internetbankingficticio.service.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.CustomerResourceNotFoundException;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;
import internetbankingficticio.test.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDaoListObject;
import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDaoObject;
import static internetbankingficticio.test.utils.customer.CustomerRepositoryMockTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void shouldReturnCustomer_whenFindCustomerByIdFindsCustomer() throws CustomerResourceNotFoundException {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, customerDao);

        CustomerDto returnedCustomerDto = customerService.findCustomerById(customerTestId);
        assertThat(returnedCustomerDto).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
    }

    @Test
    @DisplayName("Should throw CustomerResourceNotFoundException when findCustomerById() does not find a Customer")
    public void shouldThrowCustomerEntityNotFoundException_whenFindCustomerByIdDoesNotFindCustomer() {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);
        assertThrows(CustomerResourceNotFoundException.class, () -> {
            customerService.findCustomerById(customerTestId);
        });
    }

    @Test
    @DisplayName("Should return true when existsById() finds a Customer")
    public void shouldReturnTrue_whenExistsByIdFindsCustomer() {
        Long customerTestId = 1L;
        mockRepositoryExistsByIdWithBoolean(customerRepository, customerTestId, true);
        assertThat(customerService.existsById(customerTestId)).isTrue();
    }

    @Test
    @DisplayName("Should return false when existsById() does not find a Customer")
    public void shouldReturnFalse_whenExistsByIdDoesNotFindCustomer() {
        Long customerTestId = 1L;
        mockRepositoryExistsByIdWithBoolean(customerRepository, customerTestId, false);
        assertThat(customerService.existsById(customerTestId)).isFalse();
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
    public void shouldReturnCustomer_whenUpdateCustomerFindsTheCustomerToUpdate() throws CustomerResourceNotFoundException {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, customerDao);
        mockRepositorySaveWithCustomer(customerRepository, customerDao);

        CustomerDto returnedCustomer = customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());
        assertThat(returnedCustomer).isEqualTo(customerDaoToCustomerDtoMapper.map(customerDao));
    }

    @Test
    @DisplayName("Should throw CustomerResourceNotFoundException when updateCustomer() does not find the Customer to update")
    public void shouldThrowCustomerEntityNotFoundException_whenUpdateCustomerDoesNotFindTheCustomerToUpdate() {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);
        assertThrows(CustomerResourceNotFoundException.class, () -> {
            customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().build());
        });
    }


}
