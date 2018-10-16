package pl.ws.investor.processor;

import pl.ws.investor.investment.Investment;

import java.util.List;
import java.util.Optional;

class InvestmentFactory {

    private List<Investment> investments;

    InvestmentFactory(List<Investment> investments) {
        this.investments = investments;
    }

    Investment provideInvestmentProcessor(InvestmentStyle investmentStyle) throws UnsupportedOperationException {

        Optional<Investment> investment = investments.stream().filter(p -> investmentStyle.equals(p.getStyle())).findFirst();

        if(investment.isPresent()) {
            return investment.get();
        } else {
            throw new UnsupportedOperationException("Unsupported investmentStyle: "+ investmentStyle.name());
        }
    }
}
