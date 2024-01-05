package pl.ws.investor.investment;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InvestmentProcessor implements Investment {

	@Override
	public InvestmentResult invest(Collection<Found> founds, BigDecimal investedAmount, Map<Found.Type, BigDecimal> shareParams) {
		if (CollectionUtils.isEmpty(founds) || investedAmount.compareTo(BigDecimal.ZERO) <= 0) {
			return new InvestmentResult(Collections.emptyList(), investedAmount);
		}

		Map<Found.Type, PartialInvestment> aggregatePartialInvestments = new HashMap<>();
		Map<Found.Type, List<Found>> foundsByType = founds.stream()
                .collect(Collectors.groupingBy(Found::getType));

		BigDecimal unallocatedAmount = investedAmount;
		for (Map.Entry<Found.Type, List<Found>> entry : foundsByType.entrySet()) {
			BigDecimal percentageForInvestment = shareParams.get(entry.getKey());
			BigDecimal percentageAmountForUse = investedAmount.multiply(percentageForInvestment.divide(BigDecimal.valueOf(100), 2,3));

			for (Found found : entry.getValue()) {
				while (percentageAmountForUse.compareTo(found.getPrice()) >= 0) {
					unallocatedAmount = unallocatedAmount.subtract(found.getPrice());
					percentageAmountForUse = percentageAmountForUse.subtract(found.getPrice());
					aggregatePartialInvestments.merge(found.getType(),
                            new PartialInvestment(found, found.getPrice(), BigDecimal.ZERO),
                            (old, newOne) -> new PartialInvestment(found, old.partialAmount().add(newOne.partialAmount()), percentageForInvestment));
				}
			}
		}

		return new InvestmentResult(aggregatePartialInvestments.values(), unallocatedAmount);
	}
}
