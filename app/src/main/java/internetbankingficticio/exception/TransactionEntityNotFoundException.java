package internetbankingficticio.exception;

public class TransactionEntityNotFoundException extends ResourceNotFoundException {
    public TransactionEntityNotFoundException(String entityId) {
        super(entityId);
    }
}
