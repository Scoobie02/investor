package pl.ws.investor.processor;

import pl.ws.investor.domain.FoundKind;
import java.math.BigDecimal;

public class SafeInvestment extends AbstractInvestmentProcessor {

   public SafeInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(20));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(75));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(5));
   }
}
