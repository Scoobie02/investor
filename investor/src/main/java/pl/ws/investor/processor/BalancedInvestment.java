package pl.ws.investor.processor;

import pl.ws.investor.domain.FoundKind;
import java.math.BigDecimal;

class BalancedInvestment extends AbstractInvestmentProcessor {

    BalancedInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(30));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(60));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(10));
    }
}
