package bidding.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Event {
    private int id;
    private String eventName;
    private String prizeName;
    private Date eventDate;
    private List<Member> members;
    private Member winner;

    public Event(int id, String eventName, String prizeName, Date eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.prizeName = prizeName;
        this.eventDate = eventDate;
        this.members = new LinkedList<>();
        this.winner = null;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public Member getWinner() {
        return winner;
    }

    public void setWinner(Member winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", prizeName='" + prizeName + '\'' +
                ", eventDate=" + eventDate +
                ", members=" + members +
                ", winner=" + winner +
                '}';
    }
}
