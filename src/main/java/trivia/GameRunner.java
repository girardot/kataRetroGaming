package trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameRunner {

    private boolean notAWinner;

    private int seed;

    private Game game = new Game();

    public GameRunner(int seed) {
        this.seed = seed;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void run() {

        game.addNewPlayer("Chet");
        game.addNewPlayer("Pat");
        game.addNewPlayer("Sue");

        Random rand = new Random(seed);

        do {
            int roll = rand.nextInt(5) + 1;

            int i = rand.nextInt(9);

            game.roll(roll);
            if (i == 7) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);

    }

}
