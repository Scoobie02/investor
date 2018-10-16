package pl.ws.investor.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvestmentResult)) return false;
        InvestmentResult that = (InvestmentResult) o;
        return Objects.equals(partialInvestments, that.partialInvestments) &&
                Objects.equals(unallocatedAmount, that.unallocatedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partialInvestments, unallocatedAmount);
    }
}
