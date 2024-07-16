package bidding.models;

import java.util.Date;
import java.util.List;

public class Bid {
    private int memberId;
    private int eventId;
    private List<Integer> amounts;
    private Date bidDate;

    public Bid(int memberId, int eventId, List<Integer> amounts, Date bidDate) {
        this.memberId = memberId;
        this.eventId = eventId;
        this.amounts = amounts;
        this.bidDate = bidDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getEventId() {
        return eventId;
    }

    public List<Integer> getAmounts() {
        return amounts;
    }

    public Date getBidDate() {
        return bidDate;
    }
}
