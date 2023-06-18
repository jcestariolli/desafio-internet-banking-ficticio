package internetbankingficticio.exception.entity;

public class AccountEntityNotFoundException extends EntityNotFoundException {
    public AccountEntityNotFoundException(String entityId) {
        super(entityId);
    }
}
