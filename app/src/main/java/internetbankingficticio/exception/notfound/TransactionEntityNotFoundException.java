package internetbankingficticio.exception.notfound;

public class TransactionEntityNotFoundException extends ResourceNotFoundException {
    public TransactionEntityNotFoundException(String entityId) {
        super(entityId);
    }
}
