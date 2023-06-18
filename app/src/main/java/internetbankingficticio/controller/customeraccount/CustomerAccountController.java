package internetbankingficticio.controller.customeraccount;

import internetbankingficticio.dto.customeraccount.CustomerAccountCreateDto;
import internetbankingficticio.dto.customeraccount.CustomerAccountDto;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes-contas")
public class CustomerAccountController {
    @Autowired
    private CustomerAccountServiceIF customerAccountServiceIF;


    @GetMapping
    public ResponseEntity<List<CustomerAccountDto>> listAllCustomerAccounts() {
        List<CustomerAccountDto> customerAccountDtoList = customerAccountServiceIF.listAllCustomerAccounts();
        if (customerAccountDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerAccountDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerAccountDto> createCustomerAccount(@RequestBody CustomerAccountCreateDto customerAccountCreateDto) {
        CustomerAccountDto createdCustomerAccountDto = customerAccountServiceIF.createCustomerWithAccount(customerAccountCreateDto);
        return new ResponseEntity<>(createdCustomerAccountDto, HttpStatus.CREATED);
    }
}
