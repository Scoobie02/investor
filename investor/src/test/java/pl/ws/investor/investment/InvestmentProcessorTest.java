package pl.ws.investor.investment;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class InvestmentProcessorTest {

    private static final InvestmentProcessor investmentProcessor = new InvestmentProcessor();

    @Test
    public void InvalidAmountOfAllocatedMoney_NoInvestmentsDone(){
        List<Found> founds = TestFoundsProvider.caseThreeFounds();

        BigDecimal invalidAmount = new BigDecimal(-1);
        InvestmentResult investmentResult = investmentProcessor.invest(founds, invalidAmount, Investment.InvestmentStyle.SAFE.getShareParams());

        assertEquals(invalidAmount, investmentResult.unallocatedAmount());
    }

    @Test
    public void SafeInvestment_EmptyFounds_NoAllocatedMoney() {
        final BigDecimal investedAmount = BigDecimal.valueOf(1000);
        InvestmentResult investmentResult = investmentProcessor.invest(Collections.emptyList(), investedAmount, Investment.InvestmentStyle.SAFE.getShareParams());

        assertEquals(0, investmentResult.partialInvestments().size());
        assertEquals(investedAmount, investmentResult.unallocatedAmount());

    }

    @Test
    public void SafeInvestment_NoFounds_NoAllocatedMoney() {
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();
        BigDecimal investedAmount = BigDecimal.ZERO;
        InvestmentResult investmentResult = investmentProcessor.invest(founds, investedAmount, Investment.InvestmentStyle.SAFE.getShareParams());

        assertEquals(investedAmount, investmentResult.unallocatedAmount());
    }

    @Test
    public void SafeInvestmentOption_PolishPercentCheck() {
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();

        List<Found> polishFounds = founds.stream().filter(p -> Found.Type.POLISH.equals(p.getType())).collect(Collectors.toList());
        assertEquals(2, polishFounds.size());

        BigDecimal investedAmount = BigDecimal.valueOf(10000);
        InvestmentResult investmentResult = investmentProcessor.invest(polishFounds, investedAmount, Investment.InvestmentStyle.SAFE.getShareParams());

        BigDecimal actualPolishPercent = Investment.InvestmentStyle.SAFE.getShareParams().get(Found.Type.POLISH);
        BigDecimal sumDividedPolishPercent = investmentResult.partialInvestments().stream()
                .map(PartialInvestment::percent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(actualPolishPercent, sumDividedPolishPercent);

        BigDecimal expectedAmountOfInvestedFounds = investedAmount.divide(BigDecimal.valueOf(100)).multiply(actualPolishPercent);
        BigDecimal polishFoundsAfterInvesting = investmentResult.partialInvestments().stream()
                .map(PartialInvestment::partialAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        assertEquals(expectedAmountOfInvestedFounds, polishFoundsAfterInvesting);
    }

    @Test
    public void UnallocatedAmount_CornerUpValueInvestment_NotAllocatedNotRoundedAmount(){
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();

        InvestmentResult investmentResult = investmentProcessor.invest(founds, BigDecimal.valueOf(10299.99), Investment.InvestmentStyle.SAFE.getShareParams());

        assertEquals(BigDecimal.valueOf(99.99), investmentResult.unallocatedAmount());
    }

    @Test
    public void UnallocatedAmount_CornerDownRoundingValue_ExpectedAsUnallocatedWithNotInvestedMoneyMetPercentageBoundary(){
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();

        InvestmentResult investmentResult = investmentProcessor.invest(founds, BigDecimal.valueOf(1000.012), Investment.InvestmentStyle.SAFE.getShareParams());

        assertEquals(BigDecimal.valueOf(100.012), investmentResult.unallocatedAmount());
    }

    @Test
    public void CaseOneSafeInvestment_AllInvestedAmountResult() {
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();

        BigDecimal investedAmount = new BigDecimal(10000);
        InvestmentResult investmentResult = investmentProcessor.invest(founds, investedAmount, Investment.InvestmentStyle.SAFE.getShareParams());
        BigDecimal investedSum = investmentResult.partialInvestments().stream().map(PartialInvestment::partialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(investedAmount, investedSum);
    }

    @Test
    public void UnallocatedAmountAndProcessedInvestments_AreEquals_InvestedAmount(){
        Collection<Found> founds = TestFoundsProvider.caseOneAndTwoFounds();

        final BigDecimal investedAmount = BigDecimal.valueOf(10001);

        InvestmentResult investmentResult = investmentProcessor.invest(founds, investedAmount, Investment.InvestmentStyle.SAFE.getShareParams());
        BigDecimal unallocatedAmount = investmentResult.unallocatedAmount();
        BigDecimal sumOfAllocatedInvestments = investmentResult.partialInvestments().stream().map(PartialInvestment::partialAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(investedAmount, unallocatedAmount.add(sumOfAllocatedInvestments));
        assertEquals(BigDecimal.valueOf(1), unallocatedAmount);

    }

}