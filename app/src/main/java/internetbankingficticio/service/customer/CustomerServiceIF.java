package internetbankingficticio.service.customer;

import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.entity.EntityNotFoundException;

import java.util.List;

public interface CustomerServiceIF {
    List<CustomerDto> listAllCustomers();

    CustomerDto findCustomerById(Long customerId) throws EntityNotFoundException;

    boolean existsById(Long customerId);

    CustomerDto createCustomer(CustomerCreateDto customerDto);

    CustomerDto updateCustomer(Long customerId, CustomerUpdateDto customerDto) throws EntityNotFoundException;
}
