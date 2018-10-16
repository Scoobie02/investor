package pl.ws.investor.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class PartialInvestment {

    private Found found;
    private BigDecimal partialAmount;
    private BigDecimal percent;

    PartialInvestment() {
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

    void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartialInvestment)) return false;
        PartialInvestment that = (PartialInvestment) o;
        return Objects.equals(found, that.found) &&
                Objects.equals(partialAmount, that.partialAmount) &&
                Objects.equals(percent, that.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(found, partialAmount, percent);
    }
}
