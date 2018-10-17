package pl.ws.investor.investment;

import org.apache.commons.collections4.CollectionUtils;
import pl.ws.investor.domain.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractInvestment implements Investment {

    Map<FoundKind, BigDecimal> shareParams = new HashMap<>();

    @Override
    public InvestmentResult processInvest(List<Found> founds, BigDecimal investedAmount) {

        if (CollectionUtils.isEmpty(founds)) {
            return new InvestmentResult(Collections.emptyList(), investedAmount);
        }

        BigDecimal unallocatedAmount = getUnallocatedAmount(investedAmount);
        List<PartialInvestment> partialInvestments = sharePercentToPartialFounds(founds);
        calculateInvestedPartialAmount(partialInvestments, investedAmount.subtract(unallocatedAmount));

        return new InvestmentResult(partialInvestments, unallocatedAmount);
    }

    private List<PartialInvestment> sharePercentToPartialFounds(List<Found> founds) {
        List<PartialInvestment> partialInvestments = new ArrayList<>();

        shareParams.keySet().forEach(foundKind -> {
            BigDecimal percentAmount = shareParams.get(foundKind);
            partialInvestments.addAll(sharePercentBetweenSameFoundKind(founds, foundKind, percentAmount));
        });

        return partialInvestments;
    }

    private List<PartialInvestment> sharePercentBetweenSameFoundKind(List<Found> founds, FoundKind foundKind, BigDecimal givenPercentage) {
        List<Found> matchingFounds = getMatchingFounds(founds, foundKind);

        if (CollectionUtils.isEmpty(matchingFounds)) {
            return Collections.emptyList();
        }

        int matchingFoundsSize = matchingFounds.size();
        BigDecimal floorPartialPercentPerFound = divideGivenPercentagePerFound(givenPercentage, matchingFoundsSize);

        List<PartialInvestment> partialInvestments = new ArrayList<>();

        if (matchingFoundsSize > 1) {
            for (int i = matchingFoundsSize; i > 1; i--) {
                givenPercentage = givenPercentage.subtract(floorPartialPercentPerFound);
                partialInvestments.add(new PartialInvestment(matchingFounds.get(i - 1), BigDecimal.ZERO, floorPartialPercentPerFound));
            }
        }
        partialInvestments.add(new PartialInvestment(matchingFounds.get(0), BigDecimal.ZERO, givenPercentage));


        return partialInvestments;
    }

    private List<Found> getMatchingFounds(List<Found> founds, FoundKind foundKind) {
        return founds.stream().filter(p -> foundKind.equals(p.getFoundKind())).collect(Collectors.toList());
    }

    private BigDecimal divideGivenPercentagePerFound(BigDecimal givenPercentage, int matchingFoundsSize) {
        return givenPercentage.divide(BigDecimal.valueOf(matchingFoundsSize), 2, RoundingMode.FLOOR);
    }

    private void calculateInvestedPartialAmount(List<PartialInvestment> partialInvestments, BigDecimal investedAmount) {
        for (PartialInvestment p : partialInvestments) {
            BigDecimal percentageAmount = investedAmount.multiply(p.getPercent()).divide(BigDecimal.valueOf(100));
            p.setPartialAmount(percentageAmount);
        }
    }

    private BigDecimal getUnallocatedAmount(BigDecimal investedAmount) {
        return investedAmount.remainder(shareParams.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    Map<FoundKind, BigDecimal> getShareParams() {
        return shareParams;
    }
}
