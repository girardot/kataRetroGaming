package trivia;

public class Player {

    private String name;

    private int place = 0;

    private int purse = 0;

    private boolean penalized = false;

    private boolean isGettingOutOfPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isPenalized() {
        return penalized;
    }

    public void setPenalized(boolean penalized) {
        this.penalized = penalized;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
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
