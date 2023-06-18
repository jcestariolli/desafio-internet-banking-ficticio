package internetbankingficticio.enums.transaction;

public enum TransactionCommand {
    DEPOSIT("deposito"), WITHDRAW("saque");

    public final String command;

    TransactionCommand(String command) {
        this.command = command;
    }

    public static TransactionCommand valueOfCommand(String command) {
        for (TransactionCommand v : values())
            if (v.getCommand().equalsIgnoreCase(command)) return v;
        throw new IllegalArgumentException();
    }

    public String getCommand() {
        return this.command;
    }

    @Override
    public String toString() {
        return getCommand();
    }
}
