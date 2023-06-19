package internetbankingficticio.exception;

public class CustomerResourceNotFoundException extends ResourceNotFoundException {
    public CustomerResourceNotFoundException(String entityId) {
        super(entityId);
    }
}
