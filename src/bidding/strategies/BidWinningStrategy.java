package bidding.strategies;

import java.util.List;

import bidding.models.Bid;
import bidding.models.Member;

public interface BidWinningStrategy {
    Member decideWinner(List<Bid> bids);
}
