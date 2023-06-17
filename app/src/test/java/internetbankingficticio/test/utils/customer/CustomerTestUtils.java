package internetbankingficticio.test.utils.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;
import internetbankingficticio.service.customer.CustomerServiceIF;

import java.util.List;
import java.util.Optional;

import static internetbankingficticio.test.utils.TestUtils.getDateNow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerTestUtils {

    public static void mockServiceListAllCustomersWithCustomerList(CustomerServiceIF customerServiceMock, List<CustomerDto> customerDtoList) {
        when(customerServiceMock.listAllCustomers()).thenReturn(customerDtoList);
    }

    public static void mockServiceFindCustomerByIdWithCustomer(CustomerServiceIF customerServiceMock, Long customerId, CustomerDto customerDto) {
        when(customerServiceMock.findCustomerById(customerId)).thenReturn(Optional.of(customerDto));
    }

    public static void mockServiceFindCustomerByIdWithEmptyResult(CustomerServiceIF customerServiceMock, Long customerId) {
        when(customerServiceMock.findCustomerById(customerId)).thenReturn(Optional.empty());
    }

    public static void mockServiceCreateCustomerWithCustomer(CustomerServiceIF customerServiceMock, CustomerDto customerDto) {
        when(customerServiceMock.createCustomer(any(CustomerCreateDto.class))).thenReturn(customerDto);
    }

    public static void mockServiceUpdateCustomerWithCustomer(CustomerServiceIF customerServiceMock, CustomerDto customerDto) {
        when(customerServiceMock.updateCustomer(anyLong(), any(CustomerUpdateDto.class))).thenReturn(Optional.of(customerDto));
    }

    public static void mockServiceUpdateCustomerWithEmptyResult(CustomerServiceIF customerServiceMock, Long customerId, CustomerUpdateDto customerUpdateDto) {
        when(customerServiceMock.updateCustomer(customerId, customerUpdateDto)).thenReturn(Optional.empty());
    }

    public static void mockRepositoryFindAllWithCustomerList(CustomerRepository customerRepositoryMock, List<CustomerDao> customerDaoList) {
        when(customerRepositoryMock.findAll()).thenReturn(customerDaoList);
    }

    public static void mockRepositoryFindByIdWithCustomer(CustomerRepository customerRepositoryMock, Long idToMock, CustomerDao customerDao) {
        when(customerRepositoryMock.findById(idToMock)).thenReturn(Optional.of(customerDao));
    }

    public static void mockRepositoryFindByIdWithEmptyResult(CustomerRepository customerRepositoryMock, Long idToMock) {
        when(customerRepositoryMock.findById(idToMock)).thenReturn(Optional.empty());
    }

    public static void mockRepositorySaveWithCustomer(CustomerRepository customerRepositoryMock, CustomerDao customerDao) {
        when(customerRepositoryMock.save(any(CustomerDao.class))).thenReturn(customerDao);
    }

    public static void mockCustomerDaoToCustomerDtoMapperMap(CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapperMock, CustomerDto customerDto) {
        when(customerDaoToCustomerDtoMapperMock.map(any())).thenReturn(customerDto);
    }

    public static void mockCustomerCreateDtoToCustomerDaoMapperMap(CustomerCreateDtoToCustomerDaoMapper customerCreateDtoToCustomerDaoMapperMock, CustomerDao customerDao) {
        when(customerCreateDtoToCustomerDaoMapperMock.map(any(CustomerCreateDto.class))).thenReturn(customerDao);
    }

    public static void mockCustomerUpdateDtoToCustomerDaoMapperMap(CustomerUpdateDtoToCustomerDaoMapper customerUpdateDtoToCustomerDaoMapperMock, CustomerDao customerDao) {
        when(customerUpdateDtoToCustomerDaoMapperMock.map(any(CustomerUpdateDto.class))).thenReturn(customerDao);
    }

    public static List<CustomerDao> generateCustomerDaoListObject() {
        return List.of(generateCustomerDaoObject(1L, "Customer Test 1"), generateCustomerDaoObject(2L, "Customer Test 2"), generateCustomerDaoObject(3L, "Customer Test 3"));
    }

    public static CustomerDao generateCustomerDaoObject(Long id, String name) {
        return CustomerDao.builder().id(id).name(name).birthday(getDateNow()).build();
    }

    public static List<CustomerDto> generateCustomerDtoListObject() {
        return List.of(generateCustomerDtoObject(1L, "Customer Test 1"), generateCustomerDtoObject(2L, "Customer Test 2"), generateCustomerDtoObject(3L, "Customer Test 3"));
    }

    public static CustomerDto generateCustomerDtoObject(Long id, String name) {
        return CustomerDto.builder().id(id).name(name).birthday(getDateNow()).build();
    }

    public static CustomerCreateDto generateCustomerCreateDtoObject(String name) {
        return CustomerCreateDto.builder().name(name).birthday(getDateNow()).build();
    }

    public static CustomerUpdateDto generateCustomerUpdateDtoObject(String name) {
        return CustomerUpdateDto.builder().name(name).birthday(getDateNow()).build();
    }
}
