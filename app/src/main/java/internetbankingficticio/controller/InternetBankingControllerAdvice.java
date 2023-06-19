package internetbankingficticio.controller;

import internetbankingficticio.exception.DateParseException;
import internetbankingficticio.exception.validation.TransactionAmmountValidationException;
import internetbankingficticio.exception.notfound.AccountResourceNotFoundException;
import internetbankingficticio.exception.notfound.CustomerResourceNotFoundException;
import internetbankingficticio.exception.notfound.ResourceNotFoundException;
import internetbankingficticio.exception.notfound.TransactionEntityNotFoundException;
import internetbankingficticio.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternetBankingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AccountResourceNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> accountEntityNotFoundException(AccountResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Conta de numero %s nao encontrado".formatted(ex.getEntityId())).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {CustomerResourceNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> customerEntityNotFoundException(CustomerResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Cliente de id %s nao encontrado".formatted(ex.getEntityId())).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {TransactionEntityNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> transactionEntityNotFoundException(TransactionEntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Transação de id %s não encontrada".formatted(ex.getEntityId())).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> entityNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Recurso de id %s não encontrada".formatted(ex.getEntityId())).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {TransactionAmmountValidationException.class})
    public ResponseEntity<ErrorResponsePojo> transactionValidationException(TransactionAmmountValidationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Erro ao validar valor da transação. Causa: %s".formatted(ex.getMessage())).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {DateParseException.class})
    public ResponseEntity<ErrorResponsePojo> dateParseException(DateParseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Formato de data invalido. Formato esperado é sempre: %s".formatted(DateUtils.DATE_FORMAT_STRING)).status(status.value()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }


}