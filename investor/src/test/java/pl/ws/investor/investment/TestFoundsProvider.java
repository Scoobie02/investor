package pl.ws.investor.investment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class TestFoundsProvider {

    public static List<Found> caseOneAndTwoFounds() {
        List<Found> founds = new ArrayList<>();
        founds.add(new Found(1L, Found.Type.POLISH, "Fundusz Polski 1",  BigDecimal.valueOf(100)));
        founds.add(new Found(2L, Found.Type.POLISH, "Fundusz Polski 2",  BigDecimal.valueOf(100)));
        founds.add(new Found(3L, Found.Type.FOREIGN, "Fundusz Zagraniczny 1",  BigDecimal.valueOf(100)));
        founds.add(new Found(4L, Found.Type.FOREIGN, "Fundusz Zagraniczny 2",  BigDecimal.valueOf(100)));
        founds.add(new Found(5L, Found.Type.FOREIGN, "Fundusz Zagraniczny 3",  BigDecimal.valueOf(100)));
        founds.add(new Found(6L, Found.Type.CASH, "Fundusz Pieniężny 1",  BigDecimal.valueOf(100)));
        return founds;
    }

    public static List<Found> caseThreeFounds(){
        List<Found> founds = new ArrayList<>();
        founds.add(new Found(1L, Found.Type.POLISH, "Fundusz Polski 1",  BigDecimal.valueOf(100)));
        founds.add(new Found(2L, Found.Type.POLISH, "Fundusz Polski 2",  BigDecimal.valueOf(100)));
        founds.add(new Found(3L, Found.Type.POLISH, "Fundusz Polski 3",  BigDecimal.valueOf(100)));
        founds.add(new Found(4L, Found.Type.FOREIGN, "Fundusz Zagraniczny 1", BigDecimal.valueOf(100)));
        founds.add(new Found(5L, Found.Type.FOREIGN, "Fundusz Zagraniczny 2", BigDecimal.valueOf(100)));
        founds.add(new Found(6L, Found.Type.CASH, "Fundusz Pieniężny 1",  BigDecimal.valueOf(100)));
        return founds;
    }
}
