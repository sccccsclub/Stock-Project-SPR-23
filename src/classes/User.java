package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author sortizb
 * 
 * User class with username, password, email, budget, and ownedLots (which includes lots marked as favourite. That is, they don't have any Lot bought)
 */
public class User {
    String username;
    String password;
    String email;
    double budget;
    HashMap<String, LotBag> ownedLots;
    
    /**
     * Constructor
     * @param username
     * @param password
     * @param email
     * budget is set to 0.0 and the HashMap is created
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        budget = 0.0;
        ownedLots = new HashMap<>(60);
    }

    /**
     * Deposit money to the user's account (increase budget)
     * @param deposit Quantity to be deposited : double
     * @return new budget : double
     * @throws IllegalArgumentException In case trying to deposit a value smaller than 0
     */
    public double deposit(double deposit) throws IllegalArgumentException{
        if (deposit < 0.00001)
            throw new IllegalArgumentException("Can't deposite negative value.");
        budget += deposit;
        return budget;
    }

    /**
     * Buy a lot by providing ticker, price when bought, and the amount of stock bought
     * @param ticker : String
     * @param price When bought : double
     * @param amount of Stock Bought : double
     * @throws IllegalArgumentException
     */
    public void buyLot(String ticker, double price, double amount) throws IllegalArgumentException{
        if (budget < price)
            throw new IllegalArgumentException("Not enough Budget");
        Optional<LotBag> optionalLot = Optional.ofNullable(ownedLots.get(ticker));
        optionalLot.ifPresent(l -> l.addLot(price, amount));
        optionalLot.orElse(ownedLots.put(ticker, new LotBag(ticker, price, amount)));
        budget -= price;
    }

    /**
     * Sell using the LotBag.removeDefault method
     * @param ticker : String
     * @param amount to be sold : double
     * @return Initially, returns the remaining total lot. (Plans: To return current budget after implementint increase) : double
     * @throws IllegalArgumentException If trying to sell a lot you don't own 
     */
    public double sellLotDefault(String ticker, double amount) throws IllegalArgumentException{
        Optional<LotBag> optionalLotBag = Optional.ofNullable(ownedLots.get(ticker));
        if (optionalLotBag.isPresent()) {
            double remaining = optionalLotBag.get().removeLotDefault(amount);
            if (remaining < 0.0001)
                ownedLots.remove(ticker);
            return remaining;
        }
        else {
            throw new IllegalArgumentException("You don't own any Lot for the given ticker.");
        }
    }
    
    /**
     * Sells using the LotBag.removeCustom method
     * @param ticker : String
     * @param index : Selected Lot in LotBag : int
     * @param amount to be sold : double
     * @return Value of remaining amount of the selected Lot (Plans: To return current budget after implementint increase) : double
     * @throws IllegalArgumentException
     */
    public double sellLotCustom(String ticker, int index, double amount) throws IllegalArgumentException{
        Optional<LotBag> optionalLotBag = Optional.ofNullable(ownedLots.get(ticker));
        if (optionalLotBag.isPresent()) {
            return optionalLotBag.get().removeLotCustome(index, amount);
        }
        else {
            throw new IllegalArgumentException("You don't own any Lot for the given ticker.");
        }
    }

    /**
     * Finds a lot in your ownedLots by ticker
     * @param ticker : Strings
     * @return the LotBag if found. Else, returns null
     */
    public LotBag findTicker(String ticker) {
        Optional<LotBag> optionalLotBag = Optional.ofNullable(ownedLots.get(ticker));
        if (optionalLotBag.isPresent())
            return optionalLotBag.get();
        else    
            return null;
    }

    /**
     * Add a LotBag as a favourite
     * @param ticker : String
     */
    public void addFavourite(String ticker) {
        Optional<LotBag> optionalLot = Optional.ofNullable(ownedLots.get(ticker));
        optionalLot.ifPresent(l -> l.setFavouriteTrue());
        optionalLot.orElse(ownedLots.put(ticker, new LotBag(ticker)));
    }

    /**
     * Remove favourite from ownedLots if and only if its total is 0.0
     * @param ticker : String
     * @throws IllegalArgumentException for used methods
     */
    public void removeFavourite(String ticker) throws IllegalArgumentException{
        Optional<LotBag> optionalLot = Optional.ofNullable(ownedLots.get(ticker));
        optionalLot.ifPresent(l -> {
            if (l.getTotal() < 0.00001) {
                removeLot(ticker);
            }
        });
    }

    /**
     * If needed: To remove a specified lotBag from ownedLots
     * @param ticker : String
     * @return Removed LotBag
     * @throws IllegalArgumentException 
     */
    public LotBag removeLot(String ticker){
        if (ownedLots.containsKey(ticker)) {
            return ownedLots.remove(ticker);
        }
        return null;
    }

    /**
     * @return username : String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password : String
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param email new password : String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email : String
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email new email : String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return budget : double
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @return ownedLots : List<LotBag>
     */
    public List<LotBag> getOwnedLots() {
        return ownedLots.values().stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", email=" + email + ", budget=" + budget + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
}
