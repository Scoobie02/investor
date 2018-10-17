package pl.ws.investor.investmentFactory;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.ws.investor.investment.*;

import static org.junit.Assert.*;

public class InvestmentFactoryImplTest extends AbstractFactoryTest {

    @Test
    public void provideSafeDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyleType.SAFE);
        assertThat(investment, Matchers.instanceOf(SafeInvestment.class));
    }

    @Test
    public void provideBalancedDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyleType.BALANCED);
        assertThat(investment, Matchers.instanceOf(BalancedInvestment.class));
    }

    @Test
    public void provideAggressiveDivider() {
        Investment investment = investmentFactory.provideInvestmentProcessor(InvestmentStyleType.AGGRESSIVE);
        assertThat(investment, Matchers.instanceOf(AggressiveInvestment.class));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedDivideOptionTest() {
        investmentFactory.provideInvestmentProcessor(InvestmentStyleType.UNSUPPORTED);
    }
}