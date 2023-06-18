package internetbankingficticio.controller.customer;

import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.service.customer.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    @Autowired
    private CustomerServiceIF customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomers() {
        List<CustomerDto> customerDtoList = customerService.listAllCustomers();
        if (customerDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") Long customerId) {
        Optional<CustomerDto> customerAccountDtoOpt = customerService.findCustomerById(customerId);
        if (customerAccountDtoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerAccountDtoOpt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        CustomerDto createdCustomerDto = customerService.createCustomer(customerCreateDto);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerUpdateDto customerUpdateDto) {
        Optional<CustomerDto> updatedCustomerDto = customerService.updateCustomer(customerId, customerUpdateDto);
        if (updatedCustomerDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomerDto.get(), HttpStatus.OK);
    }

}
