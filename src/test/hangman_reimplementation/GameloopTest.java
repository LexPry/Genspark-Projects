package hangman_reimplementation;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameloopTest {

    @RepeatedTest(10)
    void makeSureAnswerIsLessThanDifficulty() {
        var game = new Gameloop();
        String answer = game.getAnswer();
        assertTrue(answer.length() < 8);
    }
}