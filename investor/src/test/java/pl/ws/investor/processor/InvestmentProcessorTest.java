package pl.ws.investor.processor;

import org.junit.Before;
import org.junit.Test;
import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.FoundKind;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.investment.SafeInvestment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InvestmentProcessorTest extends AbstractProcessorTest{

    @Test
    public void processInvestment() {

        List<Found> founds = new ArrayList<>();
        founds.add(new Found(1L, FoundKind.POLISH, "Fundusz Polski 1"));
        founds.add(new Found(2L, FoundKind.POLISH, "Fundusz Polski 2"));
        founds.add(new Found(3L, FoundKind.FOREIGN, "Fundusz Zagraniczny 1"));
        founds.add(new Found(4L, FoundKind.FOREIGN, "Fundusz Zagraniczny 2"));
        founds.add(new Found(5L, FoundKind.FOREIGN, "Fundusz Zagraniczny 3"));
        founds.add(new Found(6L, FoundKind.CASH, "Fundusz Pieniężny 1"));

        BigDecimal investedAmount = new BigDecimal(10000);
        InvestmentResult investmentResult = investmentProcessor.processInvestment(founds,investedAmount, InvestmentStyle.SAFE);

        SafeInvestment safeInvestment = new SafeInvestment();
        InvestmentResult safeInvestmentResult = safeInvestment.processInvest(founds, investedAmount);

        assertEquals(investmentResult, safeInvestmentResult);
    }
}