package internetbankingficticio.enums.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionCommand {
    DEPOSIT("deposito"), WITHDRAW("saque");

    public final String command;

    TransactionCommand(String command) {
        this.command = command;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TransactionCommand valueOfCommand(String command) {
        for (TransactionCommand v : values())
            if (v.getCommand().equalsIgnoreCase(command)) return v;
        throw new IllegalArgumentException();
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    @JsonValue
    public String toString() {
        return getCommand();
    }
}
