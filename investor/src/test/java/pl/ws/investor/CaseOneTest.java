package pl.ws.investor;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pl.ws.investor.processor.*;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.processor.InvestmentStyle;
import pl.ws.investor.domain.PartialInvestment;
import java.math.BigDecimal;

public class CaseOneTest extends AbstractTest{

    private final BigDecimal investedAmount = new BigDecimal(10000);

    @Before
    public void setUpFounds() {
        caseOneAndTwoFounds();
    }

    @Test
    public void safeInvestmentAmountResultTest() {
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, investedAmount, InvestmentStyle.SAFE);
        BigDecimal investedSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(investedAmount, Matchers.comparesEqualTo(investedSum));
    }

}
