package pl.ws.investor.processor;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvestmentFactoryTest {

    @Test
    public void provideSafeDivider() {
        Investment investment = InvestmentFactory.provideInvestmentProcessor(InvestmentStyle.SAFE);
        assertThat(investment, Matchers.instanceOf(SafeInvestment.class));
    }

    @Test
    public void provideBalancedDivider() {
        Investment investment = InvestmentFactory.provideInvestmentProcessor(InvestmentStyle.BALANCED);
        assertThat(investment, Matchers.instanceOf(BalancedInvestment.class));
    }

    @Test
    public void provideAggressiveDivider() {
        Investment investment = InvestmentFactory.provideInvestmentProcessor(InvestmentStyle.AGGRESSIVE);
        assertThat(investment, Matchers.instanceOf(AggressiveInvestment.class));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedDivideOptionTest() {
        InvestmentFactory.provideInvestmentProcessor(InvestmentStyle.UNSUPPORTED);
    }
}