package internetbankingficticio.exception;

public class AccountResourceNotFoundException extends ResourceNotFoundException {
    public AccountResourceNotFoundException(String entityId) {
        super(entityId);
    }
}
