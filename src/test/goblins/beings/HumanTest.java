package goblins.beings;

import goblins.utils.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    void testSetHealthWorks() {
        var human = new Human(new Position(2, 2),10,4,3,new Sprite('x'));
        human.setHealth(2);
        assertTrue(human.getAlive());
        assertEquals(2, human.getHealth());
        human.setHealth(0);
        assertFalse(human.getAlive());
    }

    @Test
    void testGetHealthWorks() {
        var human = new Human(new Position(2, 2),10,4,3,new Sprite('x'));
        assertEquals(10, human.getHealth());
    }

    @Test
    void test_hasSamePositionTrue() {
        var human = new Human(new Position(2, 2),10,4,3,new Sprite('x'));
        var goblin = new Goblin(new Position(2,2),10,10,10,new Sprite('g'));
        assertTrue(human.hasSamePosition(goblin.getPosition()));
    }

    @Test
    void test_hasSamePositionFalse() {
        var human = new Human(new Position(2, 2),10,4,3,new Sprite('x'));
        var goblin = new Goblin(new Position(3,2),10,10,10,new Sprite('g'));
        assertFalse(human.hasSamePosition(goblin.getPosition()));
    }

    @Test
    void combatWorksAsExpected() {
        var human = new Human(new Position(2,3),10,10,10,new Sprite('\u0048'));
        var goblin = new Goblin(new Position(2,3), 10, 10, 10, new Sprite('g'));
        assertTrue(goblin.getAlive());
        human.doDamage(goblin, 5);
        assertEquals(5, goblin.getHealth());
        goblin.doDamage(human,8);
        assertEquals(2, human.getHealth());
    }

    @Test
    void testSetPosition() {
        var human = new Human(new Position(2, 2),10,4,3,new Sprite('x'));
        human.setPosition(new Position(2,3));
        System.out.println(human.getPosition());
        assertEquals("Position{x = 2, y = 3}", human.getPosition().toString());
    }
}