package classesTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import classes.Lot;

public class TestLot {

    @Test
    public void testConstructor() {
        Lot lot = new Lot(25.0, 21.0);
        System.out.println(lot.getDateTime());
        assertEquals(lot.getAmount(), 21.0, 0.000001);
        assertEquals(lot.getPrice(), 25.0, 0.000001);
    }

    @Test
    public void testRemove() {
        Lot lot = new Lot(25.0, 21.0);
        assertThrows(IllegalArgumentException.class, () -> {
            lot.removeAmount(30.0);
        });
        assertEquals(lot.removeAmount(1.0), 20.0, 0.000001);
    }

}