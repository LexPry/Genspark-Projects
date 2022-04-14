package project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class checkGuessTest {

    @Test
    void checker() {
        var check = new checkGuess();
        assertEquals("Your guess is too high.\nTake a guess.", check.checker(4, 2));
        assertEquals("Your guess is too low.\nTake a guess.", check.checker(1, 5));
        assertEquals("Correct!", check.checker(4, 4));

    }
}