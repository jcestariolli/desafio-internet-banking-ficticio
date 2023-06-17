package internetbankingficticio.test.utils.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomerRepositoryMockTestUtils {

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

}
