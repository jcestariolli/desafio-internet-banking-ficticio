package internetbankingficticio.exception.notfound;

public class AccountResourceNotFoundException extends ResourceNotFoundException {
    public AccountResourceNotFoundException(String entityId) {
        super(entityId);
    }
}
