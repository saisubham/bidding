package bidding.services;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bidding.exceptions.BidLimitExceededException;
import bidding.exceptions.EntityDoesNotExistException;
import bidding.exceptions.InsufficientSuperCoinException;
import bidding.exceptions.MemberNotRegisteredException;
import bidding.models.Bid;
import bidding.models.Event;
import bidding.models.Member;
import bidding.strategies.BidWinningStrategy;

public class BidService {
    private final Map<Integer, List<Bid>> eventIdToBidsMap;
    private final MemberService memberService;
    private final EventService eventService;
    private final BidWinningStrategy bidWinningStrategy;
    private final Map<Date, Event> leaderboard;

    public BidService(MemberService memberService, EventService eventService, BidWinningStrategy bidWinningStrategy) {
        this.memberService = memberService;
        this.eventService = eventService;
        this.bidWinningStrategy = bidWinningStrategy;
        this.eventIdToBidsMap = new HashMap<>();
        leaderboard = new HashMap<>();
    }

    public Bid submitBid(int memberId, int eventId, List<Integer> amounts)
            throws BidLimitExceededException, EntityDoesNotExistException, InsufficientSuperCoinException, MemberNotRegisteredException {
        if (amounts == null || amounts.isEmpty() || amounts.size() > 5) {
            throw new BidLimitExceededException(memberId, amounts == null ? 0 : amounts.size());
        }
        Member member = this.memberService.getMemberById(memberId);
        if (member == null) {
            throw new EntityDoesNotExistException("member", String.valueOf(memberId));
        }

        if (!eventService.isMemberRegisteredToEvent(eventId, memberId)) {
            throw new MemberNotRegisteredException(eventId, memberId);
        }

        Integer maxAmount = Collections.max(amounts);
        if (member.getSuperCoin() < maxAmount) {
            throw new InsufficientSuperCoinException(memberId, maxAmount);
        }
        Event event = this.eventService.getEventById(eventId);
        if (event == null) {
            throw new EntityDoesNotExistException("event", String.valueOf(eventId));
        }
        Bid bid = new Bid(memberId, eventId, amounts, new Date());
        eventIdToBidsMap.putIfAbsent(eventId, new LinkedList<>());
        eventIdToBidsMap.get(eventId).add(bid);

        System.out.println("bids submitted successfully");

        return bid;
    }

    public List<Bid> getBidsByEventId(int eventId) {
        return eventIdToBidsMap.get(eventId);
    }

    public Member declareWinner(int eventId) {
        Event event = eventService.getEventById(eventId);
        List<Bid> bids = this.getBidsByEventId(eventId);
        Member winner = bidWinningStrategy.decideWinner(bids);
        event.setWinner(winner);
        leaderboard.put(event.getEventDate(), event);
        return winner;
    }

    public void listWinners(boolean asc) {
        List<Event> list = leaderboard.values().stream().sorted((a, b) -> {
            if (asc) {
                return a.getEventDate().before(b.getEventDate()) ? -1 : 1;
            }
            return b.getEventDate().before(a.getEventDate()) ? -1 : 1;
        }).toList();
        for (Event event : list) {
            System.out.println(event.getEventName() + ", " + event.getWinner().getName() + ", " + event.getEventDate());
        }
    }
}
