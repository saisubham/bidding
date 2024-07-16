package bidding.exceptions;

public class InsufficientSuperCoinException extends Exception {
    public InsufficientSuperCoinException(int memberId, int amount) {
        super("member: " + memberId + " does not have " + amount + " supercoins.");
    }
}
