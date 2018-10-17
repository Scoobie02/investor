package pl.ws.investor.investmentFactory;

import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.InvestmentStyle;

public interface InvestmentFactory {

    Investment provideInvestmentProcessor(InvestmentStyle investmentStyle) throws UnsupportedOperationException;
}
