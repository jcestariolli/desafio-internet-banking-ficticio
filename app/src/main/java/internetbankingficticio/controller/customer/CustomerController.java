package internetbankingficticio.controller.customer;

import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.customer.CustomerCreateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.customer.CustomerUpdateDto;
import internetbankingficticio.exception.ResourceNotFoundException;
import internetbankingficticio.service.customer.CustomerServiceIF;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    @Autowired
    private CustomerServiceIF customerService;

    @Autowired
    private CustomerAccountServiceIF customerAccountServiceIF;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listAllCustomers() {
        return new ResponseEntity<>(customerService.listAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id_cliente") Long customerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id_cliente") Long customerId, @RequestBody CustomerUpdateDto customerUpdateDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/{id_cliente}/contas")
    public ResponseEntity<List<AccountDto>> findAccountsByCustomerId(@PathVariable("id_cliente") Long customerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerAccountServiceIF.listAccountsByCustomerId(customerId), HttpStatus.OK);
    }

}
