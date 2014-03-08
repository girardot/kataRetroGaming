package trivia;

public class Player {

    private String name;

    private int place = 0;

    private int purse = 0;

    private Boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public Boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(Boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void move(int roll) {
        place = (place + roll)%12;
    }

    public void addCoin() {
        purse ++;
    }

    public boolean hasWin() {
        return purse >= 6;
    }

}
