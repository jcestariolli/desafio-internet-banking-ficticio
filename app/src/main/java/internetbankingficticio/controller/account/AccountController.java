package internetbankingficticio.controller.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.dto.customer.CustomerDto;
import internetbankingficticio.dto.transaction.TransactionCreateDto;
import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.TransactionAmmountValidationException;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.service.account.AccountServiceIF;
import internetbankingficticio.service.customeraccount.CustomerAccountServiceIF;
import internetbankingficticio.service.transaction.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static internetbankingficticio.utils.DateUtils.getDateByString;

@RestController
@RequestMapping("/contas")
public class AccountController {

    @Autowired
    private AccountServiceIF accountService;
    @Autowired
    private CustomerAccountServiceIF customerAccountServiceIF;

    @Autowired
    private TransactionServiceIF transactionServiceIF;

    @GetMapping
    public ResponseEntity<List<AccountDto>> listAllAccounts() {
        return new ResponseEntity<>(accountService.listAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{numero_conta}")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable("numero_conta") String accountId) throws EntityNotFoundException {
        return new ResponseEntity<>(accountService.findAccountById(accountId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return new ResponseEntity<>(accountService.createAccount(accountCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{numero_conta}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("numero_conta") String accountId, @RequestBody AccountUpdateDto accountUpdateDto) throws EntityNotFoundException {
        return new ResponseEntity<>(accountService.updateAccount(accountId, accountUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/{numero_conta}/clientes")
    public ResponseEntity<List<CustomerDto>> findAccountsByCustomerId(@PathVariable("numero_conta") String accountId) throws EntityNotFoundException {
        return new ResponseEntity<>(customerAccountServiceIF.listCustomersByAccountId(accountId), HttpStatus.OK);
    }

    @GetMapping("/{numero_conta}/transacoes")
    public ResponseEntity<List<TransactionDto>> findAllAccountTransactions(@PathVariable("numero_conta") String accountId, @RequestParam(value = "data_inicio", required = false) String startDateString, @RequestParam(value = "data_fim", required = false) String endDateString) throws EntityNotFoundException {
        if (!((startDateString == null) == (endDateString == null))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<TransactionDto> transactionDtoList;
        if (startDateString != null) {
            Date startDate;
            Date endDate;
            try {
                startDate = getDateByString(startDateString);
                endDate = getDateByString(endDateString);
            } catch (ParseException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            transactionDtoList = transactionServiceIF.listAllTransactionsByAccountIdAndExecutedOnBetween(accountId, startDate, endDate);
        } else {
            transactionDtoList = transactionServiceIF.listAllTransactionsByAccountId(accountId);
        }
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @PostMapping("/{numero_conta}/transacoes")
    public ResponseEntity<TransactionDto> createAccountTransaction(@PathVariable("numero_conta") String accountId, @RequestBody TransactionCreateDto transactionCreateDto) throws TransactionAmmountValidationException, EntityNotFoundException {
        transactionCreateDto.setAccountId(accountId);
        return new ResponseEntity<>(transactionServiceIF.createTransaction(transactionCreateDto), HttpStatus.OK);
    }

}
