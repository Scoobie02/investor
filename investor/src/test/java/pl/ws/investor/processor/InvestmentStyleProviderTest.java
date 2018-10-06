package pl.ws.investor.processor;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvestmentStyleProviderTest {

    @Test
    public void provideSafeDivider() {
        Investment investment = InvestmentProcessor.provideInvestmentProcessor(InvestmentStyle.SAFE);
        assertThat(investment, Matchers.instanceOf(SafeInvestmentProcessor.class));
    }

    @Test
    public void provideBalancedDivider() {
        Investment investment = InvestmentProcessor.provideInvestmentProcessor(InvestmentStyle.BALANCED);
        assertThat(investment, Matchers.instanceOf(BalancedInvestmentProcessor.class));
    }

    @Test
    public void provideAggressiveDivider() {
        Investment investment = InvestmentProcessor.provideInvestmentProcessor(InvestmentStyle.AGGRESSIVE);
        assertThat(investment, Matchers.instanceOf(AggressiveInvestmentProcessor.class));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedDivideOptionTest() {
        InvestmentProcessor.provideInvestmentProcessor(InvestmentStyle.UNSUPPORTED);
    }
}