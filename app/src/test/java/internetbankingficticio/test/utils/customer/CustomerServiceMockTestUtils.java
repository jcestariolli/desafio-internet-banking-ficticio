package internetbankingficticio.test.utils.customer;

import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.service.customer.CustomerServiceIF;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceMockTestUtils {

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
}
