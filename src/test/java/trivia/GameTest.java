package trivia;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class GameTest {

    private static boolean notAWinner;

    private static boolean oldNotAWinner;

    public void run_one_game() {

        ByteArrayOutputStream oldGameResult = new ByteArrayOutputStream();
        System.setOut(new PrintStream(oldGameResult));

        Game newGame = new Game();
        OldGame oldGame = new OldGame();

        newGame.addNewPlayer("Chet");
        newGame.addNewPlayer("Pat");
        newGame.addNewPlayer("Sue");

        oldGame.add("Chet");
        oldGame.add("Pat");
        oldGame.add("Sue");

        Random rand = new Random();

        do {
            int roll = rand.nextInt(5) + 1;
            newGame.roll(roll);

            int i = rand.nextInt(9);
            if (i == 7) {
                notAWinner = newGame.wrongAnswer();
            } else {
                notAWinner = newGame.wasCorrectlyAnswered();
            }

            oldGame.roll(roll);
            if (i == 7) {
                oldNotAWinner = oldGame.wrongAnswer();
            } else {
                oldNotAWinner = oldGame.wasCorrectlyAnswered();
            }

            assertThat(oldNotAWinner).isEqualTo(notAWinner);

        } while (notAWinner);

        assertThat(newGame.getDisplayResult()).isEqualTo(oldGameResult.toString());
    }

    @Test
    public void run_ten_games() throws Exception {
        for (int i = 0; i < 11; i++) {
            run_one_game();
        }
    }
}
