package pl.ws.investor;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.domain.PartialInvestment;
import pl.ws.investor.processor.InvestmentStyle;
import pl.ws.investor.processor.InvestmentProcessor;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CaseTwoTest extends AbstractTest{

    private final BigDecimal investedAmount = BigDecimal.valueOf(10001);

    @Before
    public void setUpFounds() {
        caseOneAndTwoFounds();
    }


    @Test
    public void unexpectedInvestedAmount(){
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, investedAmount, InvestmentStyle.SAFE);

        assertThat(BigDecimal.valueOf(1), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }


    @Test
    public void unallocatedAmount(){
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, investedAmount, InvestmentStyle.SAFE);
        BigDecimal unallocatedAmount = investmentResult.getUnallocatedAmount();
        BigDecimal sumOfAllocatedInvestments = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(investedAmount, Matchers.comparesEqualTo(unallocatedAmount.add(sumOfAllocatedInvestments)));
    }

}
