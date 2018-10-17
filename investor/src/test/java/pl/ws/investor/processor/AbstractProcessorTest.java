package pl.ws.investor.processor;

import org.junit.Before;
import pl.ws.investor.investment.Investment;
import pl.ws.investor.investment.*;
import pl.ws.investor.investmentFactory.AbstractFactoryTest;
import pl.ws.investor.investmentFactory.InvestmentFactory;
import pl.ws.investor.investmentFactory.InvestmentFactoryImpl;

import java.util.ArrayList;
import java.util.List;

class AbstractProcessorTest extends AbstractFactoryTest {

    InvestmentProcessor investmentProcessor;

    @Before
    public void setUp(){
        super.setUp();
        investmentProcessor = new InvestmentProcessor(investmentFactory);
    }

}
