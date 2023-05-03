package classesTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import classes.User;

public class TestUser {
    @Test
    public void testConstructor() {
        User user = new User("admin", "1234", "admin@sccc.com");
        assertEquals(user.getUsername(), "admin");
        assertEquals(user.getPassword(), "1234");
        assertEquals(user.getEmail(), "admin@sccc.com");
        assertEquals(user.getBudget(), 0.0, 0.000001);
        assertEquals(user.getOwnedLots().size(), 0);
    }

    @Test
    public void testDeposit() {
        User user = new User("admin", "1234", "admin@sccc.com");
        assertThrows(IllegalArgumentException.class, () -> {
            user.deposit(-210);
        });
        user.deposit(2500);
        assertEquals(user.getBudget(), 2500, 0.000001);
    }

    @Test
    public void testBuyLot() {
        User user = new User("admin", "1234", "admin@sccc.com");
        user.deposit(2500);
        assertThrows(IllegalArgumentException.class, () -> {
            user.buyLot("AAPL", 3000, 32);
        });
        user.buyLot("AAPL", 1200, 11);
        assertEquals(user.getOwnedLots().size(), 1);
        assertEquals(user.findTicker("AAPL").getTotal(), 11, 0.00001);
        assertEquals(user.getBudget(), 1300, 0.00001);
        user.buyLot("AAPL", 500, 6);
        user.buyLot("TSLA", 200, 5);
        assertEquals(user.getOwnedLots().size(), 2);
        assertEquals(user.findTicker("TSLA").getTotal(), 5, 0.000001);
    }

    @Test
    public void testSellDefault() {
        User user = new User("admin", "1234", "admin@sccc.com");
        assertThrows(IllegalArgumentException.class, () -> {
            user.sellLotDefault("APPL", 32);
        });
        user.deposit(1200);
        user.buyLot("AAPL", 900, 10);
        user.sellLotDefault("AAPL", 8);
        assertEquals(user.findTicker("AAPL").getTotal(), 2, 0.0001);
    }

    @Test
    public void testSellCustom() {
        User user = new User("admin", "1234", "admin@sccc.com");
        assertThrows(IllegalArgumentException.class, () -> {
            user.sellLotDefault("APPL", 32);
        });
        user.deposit(1200);
        user.buyLot("AAPL", 900, 10);
        user.sellLotCustom("AAPL", 0, 8);
        assertEquals(user.findTicker("AAPL").getTotal(), 2, 0.0001);
    }
}
