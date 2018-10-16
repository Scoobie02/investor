package pl.ws.investor.domain;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoundTest {

    @Test
    public void foundTest() {
        Long id = 1L;
        String name = "Fundusz Polski 1";
        FoundKind foundKind = FoundKind.POLISH;

        Found found = new Found(id, foundKind, name);

        assertEquals(found.getId(), id);
        assertEquals(found.getName(), name);
        assertEquals(found.getFoundKind(), foundKind);

    }


}