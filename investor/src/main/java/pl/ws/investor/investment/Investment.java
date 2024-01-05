package pl.ws.investor.investment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public interface Investment {

    InvestmentResult invest(Collection<Found> founds, BigDecimal investedAmount, Map<Found.Type, BigDecimal> shareParams);

    enum InvestmentStyle {
        SAFE(() -> Map.of(
                Found.Type.POLISH, BigDecimal.valueOf(20),
                Found.Type.FOREIGN, BigDecimal.valueOf(75),
                Found.Type.CASH, BigDecimal.valueOf(5))
        ),
        BALANCED(() -> Map.of(
                Found.Type.POLISH, BigDecimal.valueOf(30),
                Found.Type.FOREIGN, BigDecimal.valueOf(60),
                Found.Type.CASH, BigDecimal.valueOf(10))
        ),
        AGGRESSIVE(() -> Map.of(
                Found.Type.POLISH, BigDecimal.valueOf(40),
                Found.Type.FOREIGN, BigDecimal.valueOf(20),
                Found.Type.CASH, BigDecimal.valueOf(40))
        );

        private final Supplier<Map<Found.Type, BigDecimal>> shares;

        InvestmentStyle(Supplier<Map<Found.Type, BigDecimal>> shares) {
            this.shares = shares;
        }

        public Map<Found.Type, BigDecimal> getShareParams() {
            return shares.get();
        }
    }
}
