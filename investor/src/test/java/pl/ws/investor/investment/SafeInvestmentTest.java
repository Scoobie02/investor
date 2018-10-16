package pl.ws.investor.investment;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import pl.ws.investor.domain.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SafeInvestmentTest extends AbstractInvestmentTest {

    private final BigDecimal investedAmount = new BigDecimal(10000);

    private SafeInvestment safeInvestment;

    @Before
    public void setUp(){
        this.safeInvestment = new SafeInvestment();
        caseOneAndTwoFounds();
    }

    @Test
    public void safeInvestmentParamsTest() {

        SafeInvestment safeInvestment = new SafeInvestment();
        Map<FoundKind, BigDecimal> shareParams = safeInvestment.getShareParams();

        BigDecimal sum = shareParams.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(BigDecimal.valueOf(100), Matchers.comparesEqualTo(sum));
    }

    @Test
    public void safeInvestmentEmptyListTest() {
        InvestmentResult investmentResult = safeInvestment.processInvest(Collections.emptyList(), BigDecimal.valueOf(1000));

        assertEquals(0, investmentResult.getPartialInvestments().size());

    }

    @Test
    public void safeInvestmentNoFoundsTestTest() {
        BigDecimal investedFounds = BigDecimal.ZERO;
        InvestmentResult investmentResult = safeInvestment.processInvest(founds, investedFounds);

        assertEquals(founds.size(), investmentResult.getPartialInvestments().size());

        assertThat(investedFounds, Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

    @Test
    public void safeDivideOptionPercentTest() {
        List<Found> polishFounds = founds.stream().filter(p -> FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());
        assertEquals(2, polishFounds.size());

        InvestmentResult investmentResult = safeInvestment.processInvest(polishFounds, BigDecimal.valueOf(10000));


        assertEquals(2, investmentResult.getPartialInvestments().size());

        BigDecimal actualPolishPercent = BigDecimal.valueOf(20);

        BigDecimal sumDividedPolishPercent = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPercent).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(actualPolishPercent, Matchers.comparesEqualTo(sumDividedPolishPercent));

    }

    @Test
    public void unallocatedAmountCornerUpValue(){
        InvestmentResult investmentResult = safeInvestment.processInvest(founds, BigDecimal.valueOf(10299.99));

        assertThat(BigDecimal.valueOf(99.99), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

    @Test
    public void unallocatedAmountCornerDownValue(){
        InvestmentResult investmentResult = safeInvestment.processInvest(founds, BigDecimal.valueOf(1000.012));

        assertThat(BigDecimal.valueOf(0.012), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }

    @Test
    public void safeInvestmentInvestedAmountTest(){
        List<Found> testFounds = founds.stream().filter(p->FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        SafeInvestment safeInvestment = new SafeInvestment();
        BigDecimal polishPercentParam = safeInvestment.getShareParams().get(FoundKind.POLISH).divide(BigDecimal.valueOf(100));

        InvestmentResult investmentResult = safeInvestment.processInvest(testFounds, investedAmount);
        BigDecimal investmentsSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(polishPercentParam.multiply(investedAmount), Matchers.comparesEqualTo(investmentsSum));
    }

    @Test
    public void safeInvestmentDividePercentTest(){

        List<Found> testFounds = founds.stream().filter(p->FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        SafeInvestment safeInvestment = new SafeInvestment();
        BigDecimal polishPercentParam = safeInvestment.getShareParams().get(FoundKind.POLISH);

        InvestmentResult investmentResult = safeInvestment.processInvest(testFounds, investedAmount);
        BigDecimal percentSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPercent).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(polishPercentParam, Matchers.comparesEqualTo(percentSum));
    }

    @Test
    public void CaseOneSafeInvestmentAmountResultTest() {
        InvestmentResult investmentResult = safeInvestment.processInvest(founds, investedAmount);
        BigDecimal investedSum = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(investedAmount, Matchers.comparesEqualTo(investedSum));
    }


    @Test
    public void unexpectedInvestedAmount(){
        final BigDecimal investedAmount = BigDecimal.valueOf(10001);

        InvestmentResult investmentResult = safeInvestment.processInvest(founds, investedAmount);

        assertThat(BigDecimal.valueOf(1), Matchers.comparesEqualTo(investmentResult.getUnallocatedAmount()));
    }


    @Test
    public void unallocatedAmount(){
        final BigDecimal investedAmount = BigDecimal.valueOf(10001);

        InvestmentResult investmentResult = safeInvestment.processInvest(founds, investedAmount);
        BigDecimal unallocatedAmount = investmentResult.getUnallocatedAmount();
        BigDecimal sumOfAllocatedInvestments = investmentResult.getPartialInvestments().stream().map(PartialInvestment::getPartialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(investedAmount, Matchers.comparesEqualTo(unallocatedAmount.add(sumOfAllocatedInvestments)));
    }

    @Test
    public void safeInvestmentOptionTest(){
        List<Found> founds = caseThreeFounds();
        InvestmentResult investmentResult = safeInvestment.processInvest(founds, new BigDecimal(10000));
        List<PartialInvestment> polishPartialInvestments = investmentResult.getPartialInvestments().stream().filter(p -> FoundKind.POLISH.equals(p.getFound().getFoundKind())).collect(Collectors.toList());

        List<Found> polishFounds = founds.stream().filter(p -> FoundKind.POLISH.equals(p.getFoundKind())).collect(Collectors.toList());

        assertEquals(polishPartialInvestments.size(), polishFounds.size());
    }

}