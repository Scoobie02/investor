package pl.ws.investor.investment;

import pl.ws.investor.domain.FoundKind;
import pl.ws.investor.processor.InvestmentStyle;

import java.math.BigDecimal;

public class BalancedInvestment extends AbstractInvestment {

    public BalancedInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(30));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(60));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(10));
    }

    @Override
    public InvestmentStyle getStyle() {
        return InvestmentStyle.BALANCED;
    }
}
