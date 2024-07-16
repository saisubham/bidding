package bidding.services;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bidding.exceptions.EntityAlreadyExistsException;
import bidding.exceptions.EntityDoesNotExistException;
import bidding.models.Event;
import bidding.models.Member;

public class EventService {
    private final Map<Integer, Event> eventMap;
    private final Set<String> eventNameSet;
    private int numEvents;

    private final MemberService memberService;

    public EventService(MemberService memberService) {
        this.memberService = memberService;
        eventMap = new HashMap<>();
        numEvents = 0;
        eventNameSet = new HashSet<>();
    }

    public Event addEvent(String eventName, String prizeName, Date eventDate) throws EntityAlreadyExistsException {
        ++numEvents;
        if (eventNameSet.contains(eventName)) {
            throw new EntityAlreadyExistsException("event", eventName);
        }
        Event event = new Event(numEvents, eventName, prizeName, eventDate);
        eventMap.put(numEvents, event);
        eventNameSet.add(eventName);
        return event;
    }

    public void registerMember(int memberId, int eventId) throws EntityDoesNotExistException {
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            throw new EntityDoesNotExistException("member", String.valueOf(memberId));
        }
        Event event = this.eventMap.get(eventId);
        if (event == null) {
            throw new EntityDoesNotExistException("event", String.valueOf(eventId));
        }
        event.addMember(member);

        System.out.println(member.getName() + " registered to " + event.getEventName() + " successfully.");
    }

    public Event getEventById(int eventId) {
        return eventMap.get(eventId);
    }

    public boolean isMemberRegisteredToEvent(int eventId, int memberId) {
        Event event = eventMap.get(eventId);
        for (Member member : event.getMembers()) {
            if (member.getId() == memberId) {
                return true;
            }
        }
        return false;
    }
}
