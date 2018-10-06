package pl.ws.investor.domain;

import java.math.BigDecimal;

public class PartialInvestment {

    private Found found;
    private BigDecimal partialAmount;
    private BigDecimal percent;

    public PartialInvestment() {
    }

    public PartialInvestment(Found found, BigDecimal partialAmount, BigDecimal percent) {
        this.found = found;
        this.partialAmount = partialAmount;
        this.percent = percent;
    }

    public Found getFound() {
        return found;
    }

    public void setFound(Found found) {
        this.found = found;
    }

    public BigDecimal getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(BigDecimal partialAmount) {
        this.partialAmount = partialAmount;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
