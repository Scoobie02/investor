package pl.ws.investor.investmentFactory;

import org.junit.Before;
import pl.ws.investor.investment.AggressiveInvestment;
import pl.ws.investor.investment.BalancedInvestment;
import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.SafeInvestment;

import java.util.ArrayList;
import java.util.List;

public class AbstractFactoryTest {

    protected InvestmentFactory investmentFactory;

    @Before
    public void setUp(){
        List<Investment> investments = new ArrayList<>(3);
        investments.add(new SafeInvestment());
        investments.add(new BalancedInvestment());
        investments.add(new AggressiveInvestment());

        investmentFactory = new InvestmentFactoryImpl(investments);

    }
}
