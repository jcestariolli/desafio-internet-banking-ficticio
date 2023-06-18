package internetbankingficticio.controller;

import internetbankingficticio.exception.TransactionAmmountValidationException;
import internetbankingficticio.exception.entity.AccountEntityNotFoundException;
import internetbankingficticio.exception.entity.CustomerEntityNotFoundException;
import internetbankingficticio.exception.entity.EntityNotFoundException;
import internetbankingficticio.exception.entity.TransactionEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternetBankingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AccountEntityNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> accountEntityNotFoundException(AccountEntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Conta de numero %s nao encontrado".formatted(ex.getEntityId())).status(status.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {CustomerEntityNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> customerEntityNotFoundException(CustomerEntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Cliente de id %s nao encontrado".formatted(ex.getEntityId())).status(status.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {TransactionEntityNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> transactionEntityNotFoundException(TransactionEntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Transação de id %s não encontrada".formatted(ex.getEntityId())).status(status.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponsePojo> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Entidade de id %s não encontrada".formatted(ex.getEntityId())).status(status.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }

    @ExceptionHandler(value = {TransactionAmmountValidationException.class})
    public ResponseEntity<ErrorResponsePojo> transactionValidationException(TransactionAmmountValidationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponsePojo errorResponsePojo = ErrorResponsePojo.builder().message("Erro ao validar valor da transação. Causa: %s".formatted(ex.getMessage())).status(status.value()).path(request.getContextPath()).build();
        return new ResponseEntity<>(errorResponsePojo, status);
    }


}