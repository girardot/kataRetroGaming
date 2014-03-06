package trivia;

import com.google.common.collect.Lists;

import java.util.LinkedList;

import static trivia.Category.*;

public class Game {

    private StringBuilder displayResult = new StringBuilder();

    private LinkedList<Player> players = Lists.newLinkedList();

    private Questions popQuestions = new Questions(POP);
    private Questions scienceQuestions = new Questions(SCIENCE);
    private Questions sportsQuestions = new Questions(SPORTS);
    private Questions rockQuestions = new Questions(ROCK);

    private Player currentPlayer;

    boolean isGettingOutOfPenaltyBox;

    public Game() {
    }

    public void addNewPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        players.addLast(newPlayer);
        if(currentPlayer == null) {
            currentPlayer = newPlayer;
        }

        displayResult.append(playerName + " was added\r\n");
        displayResult.append("They are player number " + players.size() + "\r\n");
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
        if (currentCategory() == POP)
            displayResult.append(popQuestions.nextQuestion() + "\r\n");
        if (currentCategory() == SCIENCE)
            displayResult.append(scienceQuestions.nextQuestion() + "\r\n");
        if (currentCategory() == SPORTS)
            displayResult.append(sportsQuestions.nextQuestion() + "\r\n");
        if (currentCategory() == ROCK)
            displayResult.append(rockQuestions.nextQuestion() + "\r\n");
    }

    private Category currentCategory() {
        if (currentPlayer.getPlace()%4 == 0) return POP;
        if (currentPlayer.getPlace()%4 == 1) return SCIENCE;
        if (currentPlayer.getPlace()%4 == 2) return SPORTS;
        return ROCK;
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