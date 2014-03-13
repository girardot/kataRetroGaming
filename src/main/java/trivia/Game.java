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

    public Game() {
    }

    public void addNewPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        players.addLast(newPlayer);
        if (currentPlayer == null) {
            currentPlayer = newPlayer;
        }

        displayResult.append(playerName).append(" was added\r\n");
        displayResult.append("They are player number ").append(players.size()).append("\r\n");
    }

    public void roll(int roll) {
        displayResult.append(currentPlayer.getName()).append(" is the current player\r\n");
        displayResult.append("They have rolled a ").append(roll).append("\r\n");

        if (currentPlayer.isPenalized()) {
            if (roll % 2 != 0) {
                currentPlayer.setGettingOutOfPenaltyBox(true);
                displayResult.append(currentPlayer.getName()).append(" is getting out of the penalty box\r\n");
            } else {
                currentPlayer.setGettingOutOfPenaltyBox(false);
                displayResult.append(currentPlayer.getName()).append(" is not getting out of the penalty box\r\n");
            }
        }

        if (!currentPlayer.isPenalized() || currentPlayer.isGettingOutOfPenaltyBox()) {
            currentPlayer.move(roll);
            displayResult.append(currentPlayer.getName()).append("'s new location is ").append(currentPlayer.getPlace()).append("\r\n");
            displayResult.append("The category is ").append(currentCategory()).append("\r\n");
            askQuestion();
        }
    }

    private void askQuestion() {
        if (currentCategory() == POP)
            displayResult.append(popQuestions.nextQuestion());
        if (currentCategory() == SCIENCE)
            displayResult.append(scienceQuestions.nextQuestion());
        if (currentCategory() == SPORTS)
            displayResult.append(sportsQuestions.nextQuestion());
        if (currentCategory() == ROCK)
            displayResult.append(rockQuestions.nextQuestion());
        displayResult.append("\r\n");
    }

    private Category currentCategory() {
        if (currentPlayer.getPlace() % 4 == 0) return POP;
        if (currentPlayer.getPlace() % 4 == 1) return SCIENCE;
        if (currentPlayer.getPlace() % 4 == 2) return SPORTS;
        return ROCK;
    }

    public boolean wasCorrectlyAnswered() {
        boolean notAWinner = true;

        if (!currentPlayer.isPenalized() || currentPlayer.isGettingOutOfPenaltyBox()) {
            displayResult.append("Answer was correct!!!!\r\n");
            currentPlayer.addCoin();
            displayResult.append(currentPlayer.getName()).append(" now has ").append(currentPlayer.getPurse()).append(" Gold Coins.\r\n");
            notAWinner = !currentPlayer.hasWin();
        }

        currentPlayer = nextPlayer();
        return notAWinner;
    }

    public boolean wrongAnswer() {
        displayResult.append("Question was incorrectly answered\r\n");
        displayResult.append(currentPlayer.getName()).append(" was sent to the penalty box\r\n");
        currentPlayer.setPenalized(true);
        currentPlayer = nextPlayer();
        return true;
    }

    public String getDisplayResult() {
        return displayResult.toString();
    }

    private Player nextPlayer() {
        return players.get((players.indexOf(currentPlayer) + 1) % players.size());
    }

}