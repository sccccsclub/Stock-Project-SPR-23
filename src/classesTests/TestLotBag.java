package classesTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import classes.LotBag;

public class TestLotBag {
    @Test
    public void testConstructorBuying() {
        LotBag myLotBag = new LotBag("AAPL", 24.0, 13.0);
        assertEquals(myLotBag.getTicker(), "AAPL");
        assertEquals(myLotBag.getTotal(), 13.0, 0.00001);
        assertEquals(myLotBag.isFavourite(), true);
    }

    @Test
    public void testConstructorFavourite() {
        LotBag myLotBag = new LotBag("AAPL");
        assertEquals(myLotBag.getTicker(), "AAPL");
        assertEquals(myLotBag.isFavourite(), true);
        assertEquals(myLotBag.getTotal(), 0.0, 0.00001);
    }

    @Test
    public void testSetFavouriteFalse() {
        LotBag myLotBag = new LotBag("AAPL");
        myLotBag.setFavouriteFalse();
        assertEquals(myLotBag.isFavourite(), false);
    }

    @Test
    public void testSetFavouriteTrue() {
        LotBag myLotBag = new LotBag("AAPL");
        myLotBag.setFavouriteFalse();
        myLotBag.setFavouriteTrue();
        assertEquals(myLotBag.isFavourite(), true);
    }

    @Test
    public void testAddLot() {
        LotBag myLotBag = new LotBag("AAPL");
        myLotBag.addLot(12.9, 9.2);
        myLotBag.addLot(32.0, 23.7);
        assertEquals(myLotBag.isFavourite(), true);
        assertEquals(myLotBag.getTotal(), 32.9, 0.00001);
        assertEquals(myLotBag.getLotBag().size(), 2);
    }

    @Test
    public void testRemoveDefault() {
        LotBag myLotBag = new LotBag("AAPL");
        myLotBag.addLot(12.9, 9.2);
        myLotBag.addLot(32.0, 23.7);
        myLotBag.addLot(14.8, 8.8);
        System.out.println(myLotBag.getLotBag().get(2));
        assertEquals(myLotBag.removeLotDefault(9.2), 32.5, 0.0001);
        assertEquals(myLotBag.getLotBag().size(), 2);
        assertEquals(myLotBag.removeLotDefault(30.0), 2.5, 0.00001);
        assertEquals(myLotBag.getLotBag().size(), 1);
        assertThrows(IllegalArgumentException.class, () -> {
            myLotBag.removeLotDefault(60.32);
        });
    }

    @Test
    public void testRemoveCustome() {
        LotBag myLotBag = new LotBag("AAPL");
        myLotBag.addLot(12.9, 9.2);
        myLotBag.addLot(32.0, 23.7);
        myLotBag.addLot(14.8, 8.8);
        assertEquals(myLotBag.removeLotCustome(0, 4.0), 5.2, 0.00001);
        assertEquals(myLotBag.removeLotCustome(1, 16.2), 7.5, 0.00001);
        assertEquals(myLotBag.getTotal(), 21.5, 0.0001);
        assertEquals(myLotBag.removeLotCustome(2, 8.8), 0.0, 0.00001);
        assertEquals(myLotBag.getLotBag().size(), 2);
        assertThrows(IllegalArgumentException.class, () -> {
            myLotBag.removeLotCustome(0, 70.0);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myLotBag.removeLotCustome(5, 5.9);
        });
    }
}
