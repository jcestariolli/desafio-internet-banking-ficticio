package internetbankingficticio.controller.account;

import internetbankingficticio.dto.account.AccountCreateDto;
import internetbankingficticio.dto.account.AccountDto;
import internetbankingficticio.dto.account.AccountUpdateDto;
import internetbankingficticio.service.account.AccountServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class AccountController {

    @Autowired
    private AccountServiceIF accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> listAllAccounts() {
        List<AccountDto> accountDtoList = accountService.listAllAccounts();
        if (accountDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accountDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable("id") String accountId) {
        Optional<AccountDto> accountAccountDtoOpt = accountService.findAccountById(accountId);
        if (accountAccountDtoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountAccountDtoOpt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        AccountDto createdAccountDto = accountService.createAccount(accountCreateDto);
        return new ResponseEntity<>(createdAccountDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") String accountId, @RequestBody AccountUpdateDto accountUpdateDto) {
        Optional<AccountDto> updatedAccountDto = accountService.updateAccount(accountId, accountUpdateDto);
        if (updatedAccountDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAccountDto.get(), HttpStatus.OK);
    }

}
