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

import static internetbankingficticio.test.utils.customer.CustomerTestUtils.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceIntegrationTest extends AbstractTest {
    @Autowired
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapperMock;
    @MockBean
    CustomerCreateDtoToCustomerDaoMapper customerCreateDtoToCustomerDaoMapperMock;
    @MockBean
    CustomerUpdateDtoToCustomerDaoMapper customerUpdateDtoToCustomerDaoMapperMock;

    @Test
    @DisplayName("Should call repository findAll() List when listAllCustomers()")
    public void shouldCallRepositoryFindAll_whenListAllCustomers() {
        customerService.listAllCustomers();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should call repository findById() when findCustomerById()")
    public void shouldCallRepositoryFindById_whenFindCustomerById() {
        Long customerTestId = 1L;
        customerService.findCustomerById(customerTestId);
        verify(customerRepository, times(1)).findById(customerTestId);
    }

    @Test
    @DisplayName("Should call repository save() when createCustomer()")
    public void shouldCallRepositorySave_whenCreateCustomer() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockCustomerCreateDtoToCustomerDaoMapperMap(customerCreateDtoToCustomerDaoMapperMock, customerDao);

        customerService.createCustomer(CustomerCreateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());

        verify(customerRepository, times(1)).save(customerDao);
    }

    @Test
    @DisplayName("Should call repository findById() and save() when updateCustomer() finds the Customer to update")
    public void shouldCallRepositoryFindByIdAndShouldCallSave_whenUpdateCustomerFindsCustomer() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockCustomerUpdateDtoToCustomerDaoMapperMap(customerUpdateDtoToCustomerDaoMapperMock, customerDao);
        mockCustomerDaoToCustomerDtoMapperMap(customerDaoToCustomerDtoMapperMock, CustomerDto.builder().build());
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, customerDao);

        customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());

        verify(customerRepository, times(1)).findById(customerTestId);
        verify(customerRepository, times(1)).save(customerDao);
    }

    @Test
    @DisplayName("Should call repository findById() and save() when updateCustomer() does not find the Customer to update")
    public void shouldCallRepositoryFindByIdAndShouldNotCallSave_whenUpdateCustomerDoesNotFindCustomerToUpdate() {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);

        customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().build());

        verify(customerRepository, times(1)).findById(customerTestId);
        verify(customerRepository, times(0)).save(any(CustomerDao.class));
    }

}
