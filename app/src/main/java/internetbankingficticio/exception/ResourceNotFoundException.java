package internetbankingficticio.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends Exception {
    String entityId;

    public ResourceNotFoundException(String entityId) {
        super();
        this.entityId = entityId;
    }
}
