package pl.ws.investor.processor;

import org.junit.Before;
import pl.ws.investor.investment.AggressiveInvestment;
import pl.ws.investor.investment.BalancedInvestment;
import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.SafeInvestment;

import java.util.ArrayList;
import java.util.List;

class AbstractProcessorTest {

    InvestmentFactory investmentFactory;
    InvestmentProcessor investmentProcessor;

    @Before
    public void setUp(){
        List<Investment> investments = new ArrayList<>(3);
        investments.add(new SafeInvestment());
        investments.add(new BalancedInvestment());
        investments.add(new AggressiveInvestment());

        investmentFactory = new InvestmentFactory(investments);
        investmentProcessor = new InvestmentProcessor(investmentFactory);
    }

}
