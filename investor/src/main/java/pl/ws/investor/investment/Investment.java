package pl.ws.investor.investment;

import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.InvestmentResult;

import java.math.BigDecimal;
import java.util.List;

public interface Investment {

    InvestmentResult processInvest(List<Found> founds, BigDecimal investedAmount);
    InvestmentStyleType getStyle();
}
