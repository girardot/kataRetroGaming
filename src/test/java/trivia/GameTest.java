package trivia;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class GameTest {

    private static boolean notAWinner;

    private static boolean oldNotAWinner;

    private void runOneGame() {
        Random rand = new Random();
        int seed = rand.nextInt(9);
        ByteArrayOutputStream oldGameResult = new ByteArrayOutputStream();
        OldGameRunner oldGameRunner = new OldGameRunner(seed, new PrintStream(oldGameResult));
        GameRunner gameRunner = new GameRunner(seed);

        oldGameRunner.run();
        gameRunner.run();
        assertThat(gameRunner.getGame().getDisplayResult()).isEqualTo(oldGameResult.toString());
    }

    @Test
    public void run_ten_games() throws Exception {
        for (int i = 0; i < 11; i++) {
            runOneGame();
        }
    }
}
