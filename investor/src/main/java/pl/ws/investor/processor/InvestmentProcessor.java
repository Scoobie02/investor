package pl.ws.investor.processor;

import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.InvestmentResult;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class InvestmentProcessor {

    public static InvestmentResult  processInvestment(List<Found> founds, BigDecimal investedAmount, InvestmentStyle investmentStyle){

        if(BigDecimal.ZERO.compareTo(investedAmount) < 0){
            Investment investment = provideInvestmentProcessor(investmentStyle);
            return investment.processInvest(founds, investedAmount);
        } else {
            return new InvestmentResult(Collections.emptyList(), investedAmount);
        }
    }

    static Investment provideInvestmentProcessor(InvestmentStyle investmentStyle) throws UnsupportedOperationException {

        switch (investmentStyle){
            case SAFE:
                return new SafeInvestmentProcessor();
            case BALANCED:
                return new BalancedInvestmentProcessor();
            case AGGRESSIVE:
                return new AggressiveInvestmentProcessor();
            default:
                throw new UnsupportedOperationException("Unsupported investmentStyle: "+ investmentStyle.name());
        }
    }
}
