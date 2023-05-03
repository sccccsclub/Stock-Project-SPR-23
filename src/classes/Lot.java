package classes;

import java.time.LocalDateTime;

/**
 * @author sortizb
 *         Class that defines a Lot, containing the price, amount, and time when
 *         bought
 */
public class Lot {

    private double price;
    private double amount;
    private LocalDateTime dateTime;

    /**
     * 
     * @param price  when the Lot is bought
     * @param amount of Lot bought
     */
    public Lot(double price, double amount) {
        this.price = price;
        this.amount = amount;
        dateTime = LocalDateTime.now();
    }

    /**
     * @return Return the price when bought the Lot : double
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Returns the amount bought from stock : double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount Amount to remove from current amount
     * @return returns new current amount
     * @throws IllegalAtgumentException Throws exception if input is greater than current amount
     */
    public double removeAmount(double amount) throws IllegalArgumentException{
        if (amount > this.amount)
            throw new IllegalArgumentException("Requested amount greater that available lot quantity");
        this.amount -= amount;
        return this.amount;
    }

    /**
     * @return Returns date and time when the lot was bought : LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}