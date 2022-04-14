package goblins;

import goblins.beings.Goblin;
import goblins.beings.Human;
import goblins.beings.Sprite;
import goblins.utils.Position;
import goblins.utils.Render;

public class GameLoop {

    private Land space;
    private Human player;
    private Goblin[] goblins;

    /**
     * Constructor
     * Creates player object, Goblins, and Land then calls loop to handle the game loop
     */
    public GameLoop() {
        this.player = new Human(new Position(2, 2), 15, 4, 5, new Sprite('\u0048'));

        Goblin matt = new Goblin(new Position(5, 5), 15, 3, 7, new Sprite('G'));
        Goblin jeremy = new Goblin(new Position(2, 10), 10, 2, 9, new Sprite('G'));
        Goblin markyMark = new Goblin(new Position(8,2), 8, 2, 3, new Sprite('G'));
        Goblin joey = new Goblin(new Position(10,8), 9,10,13,new Sprite('G'));
        this.goblins = new Goblin[] {matt, jeremy, markyMark, joey};

        this.space = new Land(15, 15)
                .withHuman(player)
                .withGoblin(goblins)
                .withWall();

        loop();
    }

    /**
     * Handles the core game loop, renders map, takes player action, and then clears the screen as long
     * as player input does not equal "q"
     */
    public void loop() {
        while (!player.lastCommand.equals("q")) {
            space.renderSpace();
            space.movePlayer();
            Render.clearScreen();
        }
        System.out.println("Thanks for playing!");
        System.exit(0);
    }
}
