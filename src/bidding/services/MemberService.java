package bidding.services;

import java.util.HashMap;
import java.util.Map;

import bidding.models.Member;

public class MemberService {
    private final Map<Integer, Member> memberMap;
    private int numMembers;

    public MemberService() {
        this.memberMap = new HashMap<>();
        this.numMembers = 0;
    }

    public Member addMember(String name, int superCoins) {
        numMembers++;
        Member member = new Member(numMembers, name, superCoins);
        memberMap.put(numMembers, member);
        System.out.println(name + " added successfully.");
        return member;
    }


    public Member getMemberById(int memberId) {
        return memberMap.get(memberId);
    }
}
