package internetbankingficticio.exception.notfound;

public class CustomerResourceNotFoundException extends ResourceNotFoundException {
    public CustomerResourceNotFoundException(String entityId) {
        super(entityId);
    }
}
