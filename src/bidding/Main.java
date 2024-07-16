package bidding;

import java.util.Date;
import java.util.List;

import bidding.exceptions.BidLimitExceededException;
import bidding.exceptions.EntityAlreadyExistsException;
import bidding.exceptions.EntityDoesNotExistException;
import bidding.exceptions.InsufficientSuperCoinException;
import bidding.exceptions.MemberNotRegisteredException;
import bidding.models.Bid;
import bidding.models.Event;
import bidding.models.Member;
import bidding.services.BidService;
import bidding.services.EventService;
import bidding.services.MemberService;
import bidding.strategies.BidWinningStrategy;
import bidding.strategies.LowestBidWinStrategy;

public class Main {
    public static void main(String[] args) throws EntityAlreadyExistsException, EntityDoesNotExistException,
            InsufficientSuperCoinException, MemberNotRegisteredException, BidLimitExceededException {

        MemberService memberService = new MemberService();
        EventService eventService = new EventService(memberService);
        BidWinningStrategy bidWinningStrategy = new LowestBidWinStrategy(memberService);
        BidService bidService = new BidService(memberService, eventService, bidWinningStrategy);

        Member akshay = memberService.addMember("akshay", 10000);
        Member chris = memberService.addMember("chris", 5000);
        System.out.println("---");

        Event bbdEvent = eventService.addEvent("bbd", "IPHONE-14", new Date());
        eventService.registerMember(akshay.getId(), bbdEvent.getId());
        eventService.registerMember(chris.getId(), bbdEvent.getId());
        System.out.println("---");

        Bid bid1 = bidService.submitBid(akshay.getId(), bbdEvent.getId(), List.of(100, 200, 400, 500, 600));
        Bid bid2 = bidService.submitBid(chris.getId(), bbdEvent.getId(), List.of(300, 200, 400, 500, 600));
//        bidService.submitBid(10, bbdEvent.getId(), List.of(100, 200, 400, 500, 600));
        System.out.println("---");

        Member winner = bidService.declareWinner(bbdEvent.getId());
        System.out.println("winner = " + winner);
        System.out.println("---");

        bidService.listWinners(true);
    }
}
