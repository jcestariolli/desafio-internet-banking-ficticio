package internetbankingficticio.service.transaction.creator;

import internetbankingficticio.enums.transaction.TransactionCommand;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
public class TransactionCreatorServiceFactory {
    @Autowired
    private final List<TransactionCreatorServiceIF> myServices;

    Map<TransactionCommand, TransactionCreatorServiceIF> transactionCommandCreatorServiceMap;

    @Autowired // inject MyService all implementations
    public TransactionCreatorServiceFactory(List<TransactionCreatorServiceIF> transactionCreatorServiceList) {
        this.myServices = transactionCreatorServiceList;
        transactionCommandCreatorServiceMap = transactionCreatorServiceList.stream().collect(Collectors.toMap(TransactionCreatorServiceIF::getTransactionCommand, transactionCreatorServiceIF -> transactionCreatorServiceIF));
    }

    public TransactionCreatorServiceIF provide(TransactionCommand transactionCommand) {
        return transactionCommandCreatorServiceMap.get(transactionCommand);
    }


}