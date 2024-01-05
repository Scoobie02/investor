package pl.ws.investor.investment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class Found {

    private final Long id;
    private final Type type;
    private final String name;
    private final BigDecimal price;

    public enum Type {
        POLISH, FOREIGN, CASH
    }
}
