package dragonCave;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class userChoiceTest {

    @Test
    void choice() {
        var test = new userChoice();
        assertEquals(1, test.choice(1));
        assertEquals(2, test.choice(2));
    }
}