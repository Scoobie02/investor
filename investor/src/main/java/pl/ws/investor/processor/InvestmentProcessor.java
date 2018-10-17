package pl.ws.investor.processor;

import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.investment.Investment;
import pl.ws.investor.investmentFactory.InvestmentFactory;
import pl.ws.investor.investment.InvestmentStyleType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class InvestmentProcessor {

    private InvestmentFactory investmentFactory;

    InvestmentProcessor(InvestmentFactory investmentFactory) {
        this.investmentFactory = investmentFactory;
    }

    InvestmentResult processInvestment(List<Found> founds, BigDecimal investedAmount, InvestmentStyleType investmentStyleType){

        if(BigDecimal.ZERO.compareTo(investedAmount) < 0){
            Investment investment = investmentFactory.provideInvestmentProcessor(investmentStyleType);
            return investment.processInvest(founds, investedAmount);
        } else {
            return new InvestmentResult(Collections.emptyList(), investedAmount);
        }
    }


}
