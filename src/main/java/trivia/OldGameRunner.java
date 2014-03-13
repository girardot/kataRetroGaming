package trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class OldGameRunner {

    private boolean notAWinner;

    private int seed;

    private OldGame oldGame = new OldGame();

    private PrintStream stream;

    public OldGameRunner(int seed, PrintStream stream) {
        this.seed = seed;
        this.stream = stream;
        System.setOut(stream);
    }

    public void run() {
        oldGame.add("Chet");
        oldGame.add("Pat");
        oldGame.add("Sue");

        Random rand = new Random(seed);

        do {
            int roll = rand.nextInt(5) + 1;

            int i = rand.nextInt(9);

            oldGame.roll(roll);
            if (i == 7) {
                notAWinner = oldGame.wrongAnswer();
            } else {
                notAWinner = oldGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }

}
