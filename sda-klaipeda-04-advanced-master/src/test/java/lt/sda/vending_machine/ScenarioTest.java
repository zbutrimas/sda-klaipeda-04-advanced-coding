package lt.sda.vending_machine;

import lt.sda.vending_machine.models.Product;
import lt.sda.vending_machine.services.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScenarioTest {
    // IO Service created once
    private static IOService ioService;
    // DB Created for each test
    private DBService dbService;
    private CashService cashService;

    @BeforeClass
    public static void setup(){
        ioService = mock(IOService.class);
    }

    @Before
    public void setupDb(){
        dbService = new TestDB(ioService);
        cashService = new CashService(ioService, dbService);
    }

    @Test
    public void happyPath(){
        assertEquals(2, dbService.getProducts().size());
        Product testProduct = dbService.getProduct(1).get();
        assertEquals(5, dbService.getStock(testProduct));

        when(ioService.askForInput("Select coin")).thenReturn(1);

        assertEquals(10, dbService.getCoins().get(1).longValue());
        assertTrue(cashService.buyProduct(testProduct));
        assertEquals(4, dbService.getStock(testProduct));
        assertEquals(9, dbService.getCoins().get(1).longValue());
    }

    @Test
    public void testWithChange(){
        assertEquals(2, dbService.getProducts().size());
        Product testProduct = dbService.getProduct(1).get();
        assertEquals(5, dbService.getStock(testProduct));

        when(ioService.askForInput("Select coin")).thenReturn(2);

        assertEquals(2, dbService.getCoins().get(2).longValue());
        assertEquals(10, dbService.getCoins().get(1).longValue());
        assertTrue(cashService.buyProduct(testProduct));
        assertEquals(4, dbService.getStock(testProduct));
        assertEquals(1, dbService.getCoins().get(2).longValue());
        assertEquals(11, dbService.getCoins().get(1).longValue());
    }
}
