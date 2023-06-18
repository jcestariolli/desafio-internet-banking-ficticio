package internetbankingficticio.exception.entity;

import lombok.Data;

@Data
public class EntityNotFoundException extends Exception {
    String entityId;

    public EntityNotFoundException(String entityId) {
        super();
        this.entityId = entityId;
    }
}
