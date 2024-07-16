package bidding.strategies;

import java.util.Date;
import java.util.List;

import bidding.models.Bid;
import bidding.models.Member;
import bidding.services.MemberService;

public class LowestBidWinStrategy implements BidWinningStrategy {

    private final MemberService memberService;

    public LowestBidWinStrategy(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Member decideWinner(List<Bid> bids) {
        int winningBidAmount = Integer.MAX_VALUE;
        int winnerId = -1;
        Date winnerBidDate = null;

        for (Bid bid : bids) {
            int actualBidAmount = bid.getAmounts().get(0);
            for (int amount : bid.getAmounts()) {
                actualBidAmount = Math.min(actualBidAmount, amount);
            }
            if (winnerId == -1 || actualBidAmount < winningBidAmount) {
                winnerId = bid.getMemberId();
                winningBidAmount = actualBidAmount;
                winnerBidDate = bid.getBidDate();
            } else if (actualBidAmount == winningBidAmount && bid.getBidDate().before(winnerBidDate)) {
                winnerId = bid.getMemberId();
                winnerBidDate = bid.getBidDate();
            }
        }

        return memberService.getMemberById(winnerId);
    }
}
