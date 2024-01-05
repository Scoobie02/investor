package pl.ws.investor.investment;

import java.math.BigDecimal;
import java.util.Collection;

public record InvestmentResult(Collection<PartialInvestment> partialInvestments, BigDecimal unallocatedAmount) {}
