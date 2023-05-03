package classes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author sortizb
 * This class will hold many Lots in a LinkedList. All the lots in the same bag correspond to the same ticker.
 */
public class LotBag {
    private String ticker;
    private boolean favourite;
    private LinkedList<Lot> lotBag;
    private double total; //Total of lots (Adding the amount of every lot)

    /**
     * Constructor when only adding a favourite. That is, without buying.
     * @param ticker This is the ticker to identify the new LotBag : String
     * favourite will be set to true
     * total lots is equal to 0.0
     */

    //If only adding a favourite but without buying
    public LotBag(String ticker) {
        this.ticker = ticker;
        favourite = true;
        total = 0.0;
    }

    /**
     * Constructor when buying a lot
     * @param ticker Ticker to identify the new LotBag : String
     * @param price Price when bought the lot : double
     * @param amount Amount of lot bought : double
     * total = amount
     * favourite is set to true
     */
    public LotBag(String ticker, double price, double amount) {
        this.ticker = ticker;
        lotBag = new LinkedList<>();
        lotBag.addFirst(new Lot(price, amount));
        favourite = true;
        total = amount;
    }

    /**
     * Sets favourite to true
     */
    public void setFavouriteTrue() {
        favourite = true;
    }

    /**
     * Sets favourite to false
     */
    public void setFavouriteFalse() {
        favourite = false;
    }

    /**
     * Checks if this lotBag is a favourite or not
     * @return favourite : boolean
     */
    public boolean isFavourite() {
        return favourite;
    }

    /**
     * 
     * @return ticker : String
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returns the total of lots (Addition of the amount of every lot)
     * @return total : double
     */
    public double getTotal() {
        return total;
    }

    /**
     * Returns the list
     * @return lotBag : List<>()
     */
    public List<Lot> getLotBag() {
        return lotBag;
    }

    /**
     * Adds a new lot to the lotBag. It adds it to the end of the list for queue implementation. Lot is created inside the method.
     * @param price price when bought : double
     * @param amount amount of lot bought : double
     */
    public void addLot(double price, double amount) {
        if (lotBag == null)
            lotBag = new LinkedList<>();
        lotBag.addLast(new Lot(price, amount));
        total += amount;
    }

    /**
     * Will remove the desired amount of lots in order of purchase, if possible.
     * @param amount amount desired to remove : double
     * @return the total lots remaining after removing : double
     * @throws IllegalArgumentException if the input is greater than total, it will throw exception (You can't sell more than what you have)
     */
    public double removeLotDefault(double amount) throws IllegalArgumentException {
        if (amount > total)
            throw new IllegalArgumentException("Requested amount is greater than total amount of lot");
        while(amount > 0) {
            Lot lot = lotBag.getFirst();
            if (lot.getAmount() >= amount) {
                double lotAmount = lot.getAmount();
                double remainingLot = lot.removeAmount(amount);
                if (remainingLot <= 0.000001) { //Check if the lot is empty
                    lotBag.removeFirst(); //Remove if empty
                }
                total -= amount;
                amount -= lotAmount;
            }
            else {
                total -= lot.getAmount();
                amount -= lot.getAmount();
                lot.removeAmount(lot.getAmount());
                lotBag.removeFirst();
            }
        }
        return total;  
    }

    /**
     * It removes from the lot selected by user, if possible
     * @param index index of the selected lot : int
     * @param amount ampunt to be removed : double
     * @return new amount of the selected lot after removing : double
     * @throws IndexOutOfBoundsException : if index provided doesn't exist
     * @throws IllegalArgumentException : if entered amount is greater than actual amount of the selected lot (You can't sell more than what you have)
     */
    public double removeLotCustome(int index, double amount) throws IndexOutOfBoundsException, IllegalArgumentException{
        if (index >= lotBag.size())
            throw new IndexOutOfBoundsException("Invalid Index");
        Lot lot = lotBag.get(index);
        double remainingLot =  lot.removeAmount(amount);
        if (remainingLot <= 0.00000001) {
            lotBag.remove(index);
            return 0.0;
        }
        total -= amount;
        return remainingLot;
    }

    @Override
    public String toString() {
        return ticker + ": Total = " + total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LotBag other = (LotBag) obj;
        if (ticker == null) {
            if (other.ticker != null)
                return false;
        } else if (!ticker.equals(other.ticker))
            return false;
        return true;
    }
}
