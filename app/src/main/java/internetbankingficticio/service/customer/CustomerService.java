package internetbankingficticio.service.customer;

import internetbankingficticio.dao.customer.CustomerDao;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.notfound.CustomerResourceNotFoundException;
import internetbankingficticio.mapper.customer.CustomerCreateDtoToCustomerDaoMapper;
import internetbankingficticio.mapper.customer.CustomerDaoToCustomerDtoMapper;
import internetbankingficticio.mapper.customer.CustomerUpdateDtoToCustomerDaoMapper;
import internetbankingficticio.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return customerRepository.findAll().stream().map(customerDao -> customerDaoToCustomerDtoMapper.map(customerDao)).toList();
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) throws CustomerResourceNotFoundException {
        Optional<CustomerDao> customerDaoOpt = customerRepository.findById(customerId);
        if (customerDaoOpt.isEmpty()) throw new CustomerResourceNotFoundException(customerId.toString());
        return customerDaoToCustomerDtoMapper.map(customerDaoOpt.get());
    }

    @Override
    public boolean existsById(Long customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public CustomerDto createCustomer(CustomerCreateDto customerDto) {
        return customerDaoToCustomerDtoMapper.map(customerRepository.save(customerCreateDtoToCustomerDaoMapper.map(customerDto)));

    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerUpdateDto customerDto) throws CustomerResourceNotFoundException {
        if (customerRepository.findById(customerId).isEmpty())
            throw new CustomerResourceNotFoundException(customerId.toString());
        CustomerDao customerToUpdate = customerUpdateDtoToCustomerDaoMapper.map(customerDto);
        customerToUpdate.setId(customerId);
        return customerDaoToCustomerDtoMapper.map(customerRepository.save(customerToUpdate));
    }
}
