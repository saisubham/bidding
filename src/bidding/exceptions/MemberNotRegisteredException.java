package bidding.exceptions;

public class MemberNotRegisteredException extends Exception {
    public MemberNotRegisteredException(int eventId, int memberId) {
        super("member " + memberId + " is not registered in event " + eventId);
    }
}
