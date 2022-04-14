package hangman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class gameloopTest {

    @Test
    void checkAnswerReturnsTrue() {
        var a = new Gameloop();
        assertTrue(a.checkAnswer("cat", "cat"));
        assertTrue(a.checkAnswer("cat", "cta"));
    }

    @Test
    void checkAnswerReturnsFalse() {
        var a = new Gameloop();
        assertFalse(a.checkAnswer("cat", "olf"));
        assertFalse(a.checkAnswer("cat", "cap"));
    }
}