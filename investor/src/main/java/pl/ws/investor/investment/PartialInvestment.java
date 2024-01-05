package pl.ws.investor.investment;

import java.math.BigDecimal;

public record PartialInvestment(Found found, BigDecimal partialAmount, BigDecimal percent) {}
