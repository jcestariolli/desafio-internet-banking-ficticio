package internetbankingficticio.exception.entity;

public class CustomerEntityNotFoundException extends EntityNotFoundException {
    public CustomerEntityNotFoundException(String entityId) {
        super(entityId);
    }
}
