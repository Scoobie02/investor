package pl.ws.investor.investment;

import pl.ws.investor.domain.FoundKind;
import pl.ws.investor.processor.InvestmentStyle;

import java.math.BigDecimal;

public class AggressiveInvestment extends AbstractInvestment {

    public AggressiveInvestment() {
        shareParams.put(FoundKind.POLISH, BigDecimal.valueOf(40));
        shareParams.put(FoundKind.FOREIGN, BigDecimal.valueOf(20));
        shareParams.put(FoundKind.CASH, BigDecimal.valueOf(40));
    }

    @Override
    public InvestmentStyle getStyle() {
        return InvestmentStyle.AGGRESSIVE;
    }
}
