package pl.ws.investor.domain;

import java.math.BigDecimal;
import java.util.List;

public class InvestmentResult {

    private List<PartialInvestment> partialInvestments;
    private BigDecimal unallocatedAmount;

    public InvestmentResult(List<PartialInvestment> partialInvestments, BigDecimal unallocatedAmount) {
        this.partialInvestments = partialInvestments;
        this.unallocatedAmount = unallocatedAmount;
    }

    public List<PartialInvestment> getPartialInvestments() {
        return partialInvestments;
    }

    public BigDecimal getUnallocatedAmount() {
        return unallocatedAmount;
    }
}
