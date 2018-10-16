package pl.ws.investor.investment;

import pl.ws.investor.domain.FoundKind;
import pl.ws.investor.processor.InvestmentStyle;

import java.math.BigDecimal;

public class SafeInvestment extends AbstractInvestment {

   public SafeInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(20));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(75));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(5));
   }

    @Override
    public InvestmentStyle getStyle() {
        return InvestmentStyle.SAFE;
    }
}
