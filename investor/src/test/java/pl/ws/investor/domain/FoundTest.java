package pl.ws.investor.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoundTest {

    private Found found;

    @Before
    public void setUp(){
        found = new Found();
    }

    @Test
    public void getId(){
        Long idValue = 4L;
        found.setId(idValue);

        assertEquals(idValue, found.getId());
    }

    @Test
    public void getName(){
        String nameValue = "Test name";
        found.setName(nameValue);

        assertEquals(nameValue, found.getName());
    }

    @Test
    public void getFoundKind(){
        FoundKind foundKindValue = FoundKind.POLISH;
        found.setFoundKind(foundKindValue);

        assertEquals(foundKindValue, found.getFoundKind());
    }

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