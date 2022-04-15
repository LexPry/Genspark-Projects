package hangman_reimplementation;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameloopTest {

    @RepeatedTest(10)
    void makeSureAnswerIsLessThanDifficulty() {
        var game = new Gameloop();
        String answer = game.getAnswer();
        System.out.println(answer);
        assertTrue(answer.length() < game.getDifficulty());
    }

    @Test
    void makeSureCheckAnswerIsCorrect() {
        var game = new Gameloop();
        String answer = "Cow";
        String guess = "oCwfredluf";
        assertTrue(game.checkAnswer(answer, guess));

        guess = "Colfred";
        assertFalse(game.checkAnswer(answer, guess));
    }

    @Test
    void testDisplayCorrectGuesses() {
        var game = new Gameloop();
        game.displayCorrectGuesses("void", "oiplav");
        assertEquals("voi",
                game.displayCorrectGuesses("void", "kvjisao"),
                "make sure it displays in the correct order");
    }
}