package pl.ws.investor.processor;

import pl.ws.investor.domain.FoundKind;
import java.math.BigDecimal;

class AggressiveInvestment extends AbstractInvestmentProcessor {

    AggressiveInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(40));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(20));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(40));
    }

}
