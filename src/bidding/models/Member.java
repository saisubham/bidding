package bidding.models;

public class Member {
    private int id;
    private String name;
    private int superCoins;

    public Member(int id, String name, int superCoins) {
        this.id = id;
        this.name = name;
        this.superCoins = superCoins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSuperCoin() {
        return superCoins;
    }

    public void setSuperCoin(int superCoin) {
        this.superCoins = superCoin;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superCoins=" + superCoins +
                '}';
    }
}
