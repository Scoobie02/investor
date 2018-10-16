package pl.ws.investor.processor;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.ws.investor.investment.AggressiveInvestment;
import pl.ws.investor.investment.BalancedInvestment;
import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.SafeInvestment;

import static org.junit.Assert.*;

public class InvestmentFactoryTest extends AbstractProcessorTest {

    @Test
    public void provideSafeDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyle.SAFE);
        assertThat(investment, Matchers.instanceOf(SafeInvestment.class));
    }

    @Test
    public void provideBalancedDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyle.BALANCED);
        assertThat(investment, Matchers.instanceOf(BalancedInvestment.class));
    }

    @Test
    public void provideAggressiveDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyle.AGGRESSIVE);
        assertThat(investment, Matchers.instanceOf(AggressiveInvestment.class));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedDivideOptionTest() {
        investmentFactory.provideInvestmentProcessor(InvestmentStyle.UNSUPPORTED);
    }
}