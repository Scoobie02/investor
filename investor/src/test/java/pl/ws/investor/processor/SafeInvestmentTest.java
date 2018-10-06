package pl.ws.investor.processor;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.ws.investor.domain.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SafeInvestmentTest {

    private static final List<Found> founds = new ArrayList<>();


    @BeforeClass
    public static void setUpFounds() {
        founds.add(new Found(1L, FoundKind.POLISH, "Fundusz Polski 1"));
        founds.add(new Found(2L, FoundKind.POLISH, "Fundusz Polski 2"));
    }

    @Test
    public void safeInvestmentParamsTest() {

        SafeInvestmentProcessor safeInvestmentProcessor = new SafeInvestmentProcessor();
        Map<FoundKind, BigDecimal> shareParams = safeInvestmentProcessor.getShareParams();

        BigDecimal sum = shareParams.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(BigDecimal.valueOf(100), Matchers.comparesEqualTo(sum));
    }

    @Test
    public void safeInvestmentEmptyListTest() {
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(Collections.emptyList(), BigDecimal.valueOf(1000), InvestmentStyle.SAFE);

        assertEquals(0, investmentResult.getPartialInvestments().size());

    }

    @Test
    public void safeInvestmentNoFoundsTestTest() {
        BigDecimal investedFounds = BigDecimal.ZERO;
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, investedFounds, InvestmentStyle.SAFE);

        assertEquals(0, investmentResult.getPartialInvestments().size());

        assertThat(investedFounds, Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

    @Test
    public void safeDivideOptionPercentTest() {
        List<Found> polishFounds = founds.stream().filter(p -> FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());
        assertEquals(2, polishFounds.size());

        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(polishFounds, BigDecimal.valueOf(10000), InvestmentStyle.SAFE);


        assertEquals(2, investmentResult.getPartialInvestments().size());

        BigDecimal actualPolishPercent = BigDecimal.valueOf(20);

        BigDecimal sumDividedPolishPercent = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPercent).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(actualPolishPercent, Matchers.comparesEqualTo(sumDividedPolishPercent));

    }

    @Test
    public void unallocatedAmountCornerUpValue(){
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, BigDecimal.valueOf(10299.99), InvestmentStyle.SAFE);

        assertThat(BigDecimal.valueOf(99.99), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

    @Test
    public void unallocatedAmountCornerDownValue(){
        InvestmentResult investmentResult = InvestmentProcessor.processInvestment(founds, BigDecimal.valueOf(1000.012), InvestmentStyle.SAFE);

        assertThat(BigDecimal.valueOf(0.012), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

}