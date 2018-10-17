package pl.ws.investor.processor;

import org.junit.Test;
import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.domain.TestFoundsProvider;
import pl.ws.investor.investment.InvestmentStyle;
import pl.ws.investor.investment.InvestmentStyleType;
import pl.ws.investor.investment.SafeInvestment;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class InvestmentProcessorTest extends AbstractProcessorTest{

    @Test
    public void processInvestment() {

        List<Found> founds = TestFoundsProvider.caseThreeFounds();

        BigDecimal investedAmount = new BigDecimal(10000);
        InvestmentResult investmentResult = investmentProcessor.processInvestment(founds,investedAmount, InvestmentStyleType.SAFE);

        SafeInvestment safeInvestment = new SafeInvestment();
        InvestmentResult safeInvestmentResult = safeInvestment.processInvest(founds, investedAmount);

        assertEquals(investmentResult, safeInvestmentResult);
    }

    @Test
    public void invalidAmountTest(){
        List<Found> founds = TestFoundsProvider.caseThreeFounds();

        BigDecimal invalidAmount = new BigDecimal(-1);

        InvestmentResult investmentResult = investmentProcessor.processInvestment(founds, invalidAmount, InvestmentStyleType.SAFE);

        assertEquals(invalidAmount, investmentResult.getUnallocatedAmount());
    }
}