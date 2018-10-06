package pl.ws.investor;

import pl.ws.investor.domain.Found;
import pl.ws.investor.domain.FoundKind;
import java.util.ArrayList;
import java.util.List;

class AbstractTest {

    List<Found> founds = new ArrayList<>();

    void caseOneAndTwoFounds() {
        founds = new ArrayList<>();
        founds.add(new Found(1L, FoundKind.POLISH, "Fundusz Polski 1"));
        founds.add(new Found(2L, FoundKind.POLISH, "Fundusz Polski 2"));
        founds.add(new Found(3L, FoundKind.FOREIGN, "Fundusz Zagraniczny 1"));
        founds.add(new Found(4L, FoundKind.FOREIGN, "Fundusz Zagraniczny 2"));
        founds.add(new Found(5L, FoundKind.FOREIGN, "Fundusz Zagraniczny 3"));
        founds.add(new Found(6L, FoundKind.CASH, "Fundusz Pieniężny 1"));
    }

    void caseThreeFounds(){
        founds = new ArrayList<>();
        founds.add(new Found(1L, FoundKind.POLISH, "Fundusz Polski 1"));
        founds.add(new Found(2L, FoundKind.POLISH, "Fundusz Polski 2"));
        founds.add(new Found(3L, FoundKind.POLISH, "Fundusz Polski 3"));
        founds.add(new Found(4L, FoundKind.FOREIGN, "Fundusz Zagraniczny 1"));
        founds.add(new Found(5L, FoundKind.FOREIGN, "Fundusz Zagraniczny 2"));
        founds.add(new Found(6L, FoundKind.CASH, "Fundusz Pieniężny 1"));

    }
}
