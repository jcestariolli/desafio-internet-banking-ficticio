package internetbankingficticio.service.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.notfound.CustomerResourceNotFoundException;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;
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

import static internetbankingficticio.test.utils.customer.CustomerObjectsTestUtils.generateCustomerDaoObject;
import static internetbankingficticio.test.utils.customer.CustomerRepositoryMockTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceIntegrationTest extends AbstractTest {
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
    void shouldCallRepositoryFindAll_whenListAllCustomers() {
        customerService.listAllCustomers();
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should call repository findById() when findCustomerById()")
    void shouldCallRepositoryFindById_whenFindCustomerById() throws ResourceNotFoundException {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithCustomer(customerRepository, customerTestId, generateCustomerDaoObject(customerTestId, "Customer Test 1"));
        customerService.findCustomerById(customerTestId);
        verify(customerRepository, times(1)).findById(customerTestId);
    }

    @Test
    @DisplayName("Should call repository existsById() when existsById()")
    void shouldCallRepositoryExistsById_whenExistsById() {
        Long customerTestId = 1L;
        customerService.existsById(customerTestId);
        verify(customerRepository, times(1)).existsById(customerTestId);
    }

    @Test
    @DisplayName("Should call repository save() when createCustomer()")
    void shouldCallRepositorySave_whenCreateCustomer() {
        Long customerTestId = 1L;
        CustomerDao customerDao = generateCustomerDaoObject(customerTestId, "Customer Test 1");
        mockCustomerCreateDtoToCustomerDaoMapperMap(customerCreateDtoToCustomerDaoMapperMock, customerDao);

        customerService.createCustomer(CustomerCreateDto.builder().name(customerDao.getName()).birthday(customerDao.getBirthday()).build());

        verify(customerRepository, times(1)).save(customerDao);
    }

    @Test
    @DisplayName("Should call repository findById() and save() when updateCustomer() finds the Customer to update")
    void shouldCallRepositoryFindByIdAndShouldCallSave_whenUpdateCustomerFindsCustomer() throws CustomerResourceNotFoundException {
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
    void shouldCallRepositoryFindByIdAndShouldNotCallSave_whenUpdateCustomerDoesNotFindCustomerToUpdate() throws CustomerResourceNotFoundException {
        Long customerTestId = 1L;
        mockRepositoryFindByIdWithEmptyResult(customerRepository, customerTestId);
        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.updateCustomer(customerTestId, CustomerUpdateDto.builder().build());
        });
        verify(customerRepository, times(1)).findById(customerTestId);
        verify(customerRepository, times(0)).save(any(CustomerDao.class));
    }

}
