package internetbankingficticio.service.customer;

import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceIF {
    List<CustomerDto> listAllCustomers();

    Optional<CustomerDto> findCustomerById(Long customerId);
    boolean existsById(Long customerId);

    CustomerDto createCustomer(CustomerCreateDto customerDto);

    Optional<CustomerDto> updateCustomer(Long customerId, CustomerUpdateDto customerDto);
}
