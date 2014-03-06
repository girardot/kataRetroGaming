package trivia;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private StringBuilder displayResult = new StringBuilder();

    List<Player> players = Lists.newArrayList();

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    Player currentPlayer;

    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void addNewPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
        displayResult.append(playerName + " was added\r\n");
        displayResult.append("They are player number " + players.size() + "\r\n");
        if(currentPlayer == null) {
            currentPlayer = newPlayer;
        }
    }

    public void roll(int roll) {
        displayResult.append(currentPlayer.getName() + " is the current player\r\n");
        displayResult.append("They have rolled a " + roll + "\r\n");

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                displayResult.append(currentPlayer.getName() + " is getting out of the penalty box\r\n");

                currentPlayer.move(roll);

                displayResult.append(currentPlayer.getName() + "'s new location is " + currentPlayer.getPlace() + "\r\n");
                displayResult.append("The category is " + currentCategory() + "\r\n");
                askQuestion();
            } else {
                displayResult.append(currentPlayer.getName() + " is not getting out of the penalty box\r\n");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            currentPlayer.move(roll);
            displayResult.append(currentPlayer.getName() + "'s new location is " + currentPlayer.getPlace() + "\r\n");
            displayResult.append("The category is " + currentCategory() + "\r\n");
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            displayResult.append(popQuestions.removeFirst() + "\r\n");
        if (currentCategory() == "Science")
            displayResult.append(scienceQuestions.removeFirst() + "\r\n");
        if (currentCategory() == "Sports")
            displayResult.append(sportsQuestions.removeFirst() + "\r\n");
        if (currentCategory() == "Rock")
            displayResult.append(rockQuestions.removeFirst() + "\r\n");
    }

    private String currentCategory() {
        if (currentPlayer.getPlace() == 0) return "Pop";
        if (currentPlayer.getPlace() == 4) return "Pop";
        if (currentPlayer.getPlace() == 8) return "Pop";
        if (currentPlayer.getPlace() == 1) return "Science";
        if (currentPlayer.getPlace() == 5) return "Science";
        if (currentPlayer.getPlace() == 9) return "Science";
        if (currentPlayer.getPlace() == 2) return "Sports";
        if (currentPlayer.getPlace() == 6) return "Sports";
        if (currentPlayer.getPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                displayResult.append("Answer was correct!!!!\r\n");

                currentPlayer.addCoin();

                displayResult.append(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.\r\n");

                boolean winner = !(currentPlayer.getPurse() == 6);

                currentPlayer = nextPlayer();
                return winner;
            } else {
                currentPlayer = nextPlayer();
                return true;
            }
        } else {

            displayResult.append("Answer was corrent!!!!\r\n");
            currentPlayer.addCoin();
            displayResult.append(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.\r\n");

            boolean winner = !(currentPlayer.getPurse() == 6);
            currentPlayer = nextPlayer();
            return winner;
        }
    }

    public boolean wrongAnswer() {
        displayResult.append("Question was incorrectly answered\r\n");
        displayResult.append(currentPlayer.getName() + " was sent to the penalty box\r\n");
        currentPlayer.setInPenaltyBox(true);
        currentPlayer = nextPlayer();
        return true;
    }

    public String getDisplayResult() {
        return displayResult.toString();  //To change body of created methods use File | Settings | File Templates.
    }

    private Player nextPlayer() {
        return players.get((players.indexOf(currentPlayer) + 1)%players.size());
    }

}