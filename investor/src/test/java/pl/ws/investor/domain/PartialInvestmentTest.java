package pl.ws.investor.domain;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PartialInvestmentTest {

    private PartialInvestment partialInvestment;

    @Before
    public void setUp(){
        partialInvestment = new PartialInvestment();
    }

    @Test
    public void partialFoundTest() {

        Found found = new Found(567L, FoundKind.FOREIGN, "Fundusz Zagraniczny 3");
        BigDecimal partialAmount = BigDecimal.ONE;
        BigDecimal percent = new BigDecimal(25);

        partialInvestment.setFound(found);
        partialInvestment.setPartialAmount(partialAmount);
        partialInvestment.setPercent(percent);

        assertEquals(found, partialInvestment.getFound());
        assertEquals(partialAmount, partialInvestment.getPartialAmount());
        assertEquals(percent, partialInvestment.getPercent());
    }

}