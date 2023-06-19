package internetbankingficticio.test.utils.customer;

import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.CustomerResourceNotFoundException;
import internetbankingficticio.exception.ResourceNotFoundException;
import internetbankingficticio.service.customer.CustomerServiceIF;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceMockTestUtils {

    public static void mockServiceListAllCustomersWithCustomerList(CustomerServiceIF customerServiceMock, List<CustomerDto> customerDtoList) {
        when(customerServiceMock.listAllCustomers()).thenReturn(customerDtoList);
    }

    public static void mockServiceFindCustomerByIdWithCustomer(CustomerServiceIF customerServiceMock, Long customerId, CustomerDto customerDto) throws ResourceNotFoundException {
        when(customerServiceMock.findCustomerById(customerId)).thenReturn(customerDto);
    }

    public static void mockServiceFindCustomerByIdThrowCustomerNotFoundExcept(CustomerServiceIF customerServiceMock, Long customerId) throws ResourceNotFoundException {
        when(customerServiceMock.findCustomerById(customerId)).thenThrow(new CustomerResourceNotFoundException(customerId.toString()));
    }

    public static void mockServiceCreateCustomerWithCustomer(CustomerServiceIF customerServiceMock, CustomerDto customerDto) {
        when(customerServiceMock.createCustomer(any(CustomerCreateDto.class))).thenReturn(customerDto);
    }

    public static void mockServiceUpdateCustomerWithCustomer(CustomerServiceIF customerServiceMock, CustomerDto customerDto) throws ResourceNotFoundException {
        when(customerServiceMock.updateCustomer(anyLong(), any(CustomerUpdateDto.class))).thenReturn(customerDto);
    }

    public static void mockServiceUpdateCustomerCustomerNotFoundExcept(CustomerServiceIF customerServiceMock, Long customerId) throws ResourceNotFoundException {
        when(customerServiceMock.updateCustomer(any(), any())).thenThrow(new CustomerResourceNotFoundException(customerId.toString()));
    }
}
