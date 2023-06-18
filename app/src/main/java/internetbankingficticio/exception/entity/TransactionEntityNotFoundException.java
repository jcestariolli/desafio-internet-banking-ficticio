package internetbankingficticio.exception.entity;

public class TransactionEntityNotFoundException extends EntityNotFoundException {
    public TransactionEntityNotFoundException(String entityId) {
        super(entityId);
    }
}
