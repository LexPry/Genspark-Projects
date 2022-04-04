package hangman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class gameLoopTest {

    @Test
    void checkAnswerGivesRightResults() {
        var a = new gameLoop();
        assertTrue(a.checkAnswer("cat", "cat"));
        assertTrue(a.checkAnswer("cat", "cta"));
        assertFalse(a.checkAnswer("cat", "olf"));
        assertFalse(a.checkAnswer("cat", "cap"));
    }
}