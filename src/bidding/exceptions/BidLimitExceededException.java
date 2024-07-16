package bidding.exceptions;

public class BidLimitExceededException extends Exception {
    public BidLimitExceededException(int memberId, int size) {
        super(memberId + ": " + size);
    }
}
