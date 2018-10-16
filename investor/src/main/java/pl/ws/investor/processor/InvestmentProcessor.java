package pl.ws.investor.processor;

import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.investment.Investment;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class InvestmentProcessor {

    private InvestmentFactory investmentFactory;

    InvestmentProcessor(InvestmentFactory investmentFactory) {
        this.investmentFactory = investmentFactory;
    }

    InvestmentResult processInvestment(List<Found> founds, BigDecimal investedAmount, InvestmentStyle investmentStyle){

        if(BigDecimal.ZERO.compareTo(investedAmount) < 0){
            Investment investment = investmentFactory.provideInvestmentProcessor(investmentStyle);
            return investment.processInvest(founds, investedAmount);
        } else {
            return new InvestmentResult(Collections.emptyList(), investedAmount);
        }
    }


}
