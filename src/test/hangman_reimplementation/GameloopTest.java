package hangman_reimplementation;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameloopTest
    {

    // made a constructor with a boolean value for testing

    @RepeatedTest(10)
    void makeSureAnswerIsLessThanDifficulty()
        {
        var game = new Gameloop(true);
        String answer = game.getAnswer();
        System.out.println(answer);
        assertTrue(answer.length() < game.getAnswerLengthCap());
        }

    @Test
    void makeSureCheckAnswerIsCorrect()
        {
        var game = new Gameloop(true);
        String answer = "Cow";
        String guess = "oCwfredluf";
        assertTrue(game.checkAnswer(answer, guess));

        guess = "Colfred";
        assertFalse(game.checkAnswer(answer, guess));
        }

    @Test
    void testDisplayCorrectGuesses()
        {
        var game = new Gameloop(true);
        game.displayCorrectGuesses("void", "oiplav");
        assertEquals("voi _ ",
                game.displayCorrectGuesses("void", "kvjisao"),
                "make sure it displays in the correct order");
        }

    @RepeatedTest(10)
    void getAnswerLengthCap()
        {
        var game = new Gameloop(true);
        game.setAnswerLengthCap(5);
        String answer = game.getAnswer();
        assertTrue(game.getAnswerLengthCap() == 5);
        assertTrue(answer.length() <= 5);
        }

    @Test
    void setName()
        {
        var game = new Gameloop(true);
        game.setName("lex");
        assertEquals("lex",game.getName());
        }

    @Test
    void getCurrentGuess()
        {
        var game = new Gameloop(true);
        game.setCurrentGuess("l");
        assertEquals("l", game.getCurrentGuess());
        }
    }