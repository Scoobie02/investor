package pl.ws.investor;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pl.ws.investor.domain.InvestmentResult;
import pl.ws.investor.processor.InvestmentStyle;
import pl.ws.investor.processor.InvestmentProcessor;
import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.FoundKind;
import pl.ws.investor.domain.PartialInvestment;
import pl.ws.investor.processor.SafeInvestmentProcessor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CaseThreeTest extends AbstractTest {

    private final BigDecimal investedAmount = new BigDecimal(10000);

    @Before
    public void setUp(){
        caseThreeFounds();
    }

    @Test
    public void safeInvestmentOptionTest(){
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, investedAmount, InvestmentStyle.SAFE);
        List<PartialInvestment> polishPartialInvestments = investmentResult.getPartialInvestments().stream().filter(p -> FoundKind.POLISH.equals(p.getFound().getFoundKind())).collect(Collectors.toList());

        List<Found> polishFounds = founds.stream().filter(p -> FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        assertEquals(polishPartialInvestments.size(), polishFounds.size());
    }

    @Test
    public void safeInvestmentInvestedAmountTest(){
        List<Found> testFounds = founds.stream().filter(p->FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        SafeInvestmentProcessor safeInvestmentProcessor = new SafeInvestmentProcessor();
        BigDecimal polishPercentParam = safeInvestmentProcessor.getShareParams().get(FoundKind.POLISH).divide(BigDecimal.valueOf(100));

        InvestmentResult investmentResult = safeInvestmentProcessor.processInvest(testFounds, investedAmount);
        BigDecimal investmentsSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(polishPercentParam.multiply(investedAmount), Matchers.comparesEqualTo(investmentsSum));
    }

    @Test
    public void safeInvestmentDividePercentTest(){

        List<Found> testFounds = founds.stream().filter(p->FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        SafeInvestmentProcessor safeInvestmentProcessor = new SafeInvestmentProcessor();
        BigDecimal polishPercentParam = safeInvestmentProcessor.getShareParams().get(FoundKind.POLISH);

        InvestmentResult investmentResult = safeInvestmentProcessor.processInvest(testFounds, investedAmount);
        BigDecimal percentSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPercent).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(polishPercentParam, Matchers.comparesEqualTo(percentSum));
    }



}
