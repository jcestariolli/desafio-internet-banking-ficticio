package internetbankingficticio.service.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CustomerServiceIF {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerDaoToCustomerDtoMapper customerDaoToCustomerDtoMapper;
    @Autowired
    CustomerCreateDtoToCustomerDaoMapper customerCreateDtoToCustomerDaoMapper;
    @Autowired
    CustomerUpdateDtoToCustomerDaoMapper customerUpdateDtoToCustomerDaoMapper;

    @Override
    public List<CustomerDto> listAllCustomers() {
        return customerRepository.findAll().stream().map(customerDao -> customerDaoToCustomerDtoMapper.map(customerDao)).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDto> findCustomerById(Long customerId) {
        Optional<CustomerDao> customerDaoOpt = customerRepository.findById(customerId);
        return customerDaoOpt.isEmpty() ? Optional.empty() : Optional.of(customerDaoToCustomerDtoMapper.map(customerDaoOpt.get()));
    }

    @Override
    public CustomerDto createCustomer(CustomerCreateDto customerDto) {
        return customerDaoToCustomerDtoMapper.map(customerRepository.save(customerCreateDtoToCustomerDaoMapper.map(customerDto)));

    }

    @Override
    public Optional<CustomerDto> updateCustomer(Long customerId, CustomerUpdateDto customerDto) {
        if (customerRepository.findById(customerId).isPresent()) {
            CustomerDao customerToUpdate = customerUpdateDtoToCustomerDaoMapper.map(customerDto);
            customerToUpdate.setId(customerId);
            return Optional.of(customerDaoToCustomerDtoMapper.map(customerRepository.save(customerToUpdate)));
        }
        return Optional.empty();
    }
}
