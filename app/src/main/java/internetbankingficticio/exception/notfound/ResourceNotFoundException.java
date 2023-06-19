package internetbankingficticio.exception.notfound;

import lombok.Data;

@Data
public class ResourceNotFoundException extends Exception {
    final String entityId;

    public ResourceNotFoundException(String entityId) {
        super();
        this.entityId = entityId;
    }
}
