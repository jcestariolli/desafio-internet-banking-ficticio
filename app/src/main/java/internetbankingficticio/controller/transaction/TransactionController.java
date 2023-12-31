package internetbankingficticio.controller.transaction;

import internetbankingficticio.dto.transaction.TransactionDto;
import internetbankingficticio.exception.DateParseException;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;
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
@RequestMapping("/transacoes")
public class TransactionController {

    @Autowired
    private TransactionServiceIF transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> listAllTransactions(@RequestParam(value = "data_inicio", required = false) String startDateString, @RequestParam(value = "data_fim", required = false) String endDateString) throws DateParseException {
        if ((startDateString == null) != (endDateString == null)) {
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
                throw new DateParseException();
            }
            transactionDtoList = transactionService.listAllTransactionsByExecutedOnBetween(startDate, endDate);
        } else {
            transactionDtoList = transactionService.listAllTransactions();
        }
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findTransactionById(@PathVariable("id") Long transactionId) throws ResourceNotFoundException {
        return new ResponseEntity<>(transactionService.findTransactionById(transactionId), HttpStatus.OK);
    }

}
