package pl.ws.investor.investmentFactory;

import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.InvestmentStyle;

import java.util.List;
import java.util.Optional;

public class InvestmentFactoryImpl implements InvestmentFactory {

    private List<Investment> investments;

    InvestmentFactoryImpl(List<Investment> investments) {
        this.investments = investments;
    }

    public Investment provideInvestmentProcessor(InvestmentStyle investmentStyle) throws UnsupportedOperationException {

        Optional<Investment> investment = investments.stream().filter(p -> investmentStyle.equals(p.getStyle())).findFirst();

        if(investment.isPresent()) {
            return investment.get();
        } else {
            throw new UnsupportedOperationException("Unsupported investmentStyle: "+ investmentStyle);
        }
    }
}
