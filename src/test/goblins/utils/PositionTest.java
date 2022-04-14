package goblins.utils;

import goblins.beings.Human;
import goblins.beings.Sprite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void setPosition() {
        var human = new Human(new Position(2, 2), 10, 4, 3, new Sprite('x'));
        human.setPosition(new Position(2, 3));
        Assertions.assertEquals("Position{x = 2, y = 3}", human.getPosition().toString());
    }

    @Test
    void getX() {
        var human = new Human(new Position(2, 2), 10, 4, 3, new Sprite('x'));
        Assertions.assertEquals(2, human.getPosition().getX());
    }

    @Test
    void getY() {
        var human = new Human(new Position(2, 5), 10, 4, 3, new Sprite('x'));
        Assertions.assertEquals(5, human.getPosition().getY());
    }

    @Test
    void testToString() {
        var human = new Human(new Position(2, 5), 10, 4, 3, new Sprite('x'));
        Assertions.assertEquals("Position{x = 2, y = 5}", human.getPosition().toString());
    }
}