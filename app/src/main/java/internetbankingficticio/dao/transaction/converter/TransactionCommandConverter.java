package internetbankingficticio.dao.transaction.converter;

import internetbankingficticio.enums.transaction.TransactionCommand;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TransactionCommandConverter implements AttributeConverter<TransactionCommand, String> {
    @Override
    public String convertToDatabaseColumn(TransactionCommand transactionCommand) {
        return transactionCommand == null
                ? null
                : transactionCommand.getCommand();
    }

    @Override
    public TransactionCommand convertToEntityAttribute(String command) {
        if (command == null || command.isEmpty()) {
            return null;
        }
        return TransactionCommand.valueOfCommand(command);
    }
}
