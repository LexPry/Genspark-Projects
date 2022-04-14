package goblins;

import goblins.beings.Goblin;
import goblins.beings.Human;
import goblins.beings.Sprite;
import goblins.utils.Position;
import goblins.utils.Render;

import java.util.Arrays;

public class Land {

    // variables
    private final Sprite[][] space;
    private final int maxColumns;
    private final int maxRows;
    private Sprite previousSprite;
    private Human player;
    private Goblin[] allGoblins;
    public final int maxCharacterAmount;
    private final Sprite defaultSprite = new Sprite('\u03A6');
    private final Sprite wallSprite = new Sprite('\u00F7');

    // constructor
    public Land(int rows, int columns) {
        this.maxColumns = columns;
        this.maxRows = rows;
        this.space = new Sprite[maxRows][maxColumns];
        this.maxCharacterAmount = maxColumns * maxRows;

        initLand();
    }

    // methods

    /**
     * Assigns every position the default land sprite
     *
     * @return returns Sprite[][] space with all spaces filled
     */
    private Sprite[][] initLand() {
        try {
            for (int i = 0; i < maxColumns; i++) {
                for (int j = 0; j < maxRows; j++) {
                    space[i][j] = new Sprite(defaultSprite.sprite);
                }
            }
        } catch (Exception e) {
            System.out.println("Error Detected: ");
            e.printStackTrace();
        }

        return space;
    }

    /**
     * Takes the position of the object passed into it and reassigns the location to the sprite
     * of the current object
     *
     * @param position current position of this
     * @param sprite   the UTF character associated with this
     * @return returns land so the current position is updated with the correct sprite
     */
    public Land modifySpaceAtLocation(Position position, Sprite sprite) {
        previousSprite = new Sprite(space[position.getY()][position.getX()].sprite);
        space[position.getY()][position.getX()] = sprite;
        return this;
    }

    public void movePlayer() {
        isDead();
        Position previous = new Position(player.getPosition().getX(), player.getPosition().getY());
        if (gameWinCheck()) {
            System.out.println("Congratulations!!\n YOU WIN!");
            System.exit(0);
        }
        player.userInput();
        if (!collidedWithWall()) {
            modifySpaceAtLocation(previous, previousSprite);
            modifySpaceAtLocation(player.getPosition(), player.sprite);
        }

        Goblin goblinToFight = collidedWithGoblin();
        if (collidedWithGoblin() != null) player.combat(goblinToFight);
    }

    /**
     * ensures the player will not be able to go out of bounds by setting the player back to the previous
     * position instead of allowing them to continue onto the wall
     *
     * @return true or false depending on whether the player will collide with wall
     */
    private boolean collidedWithWall() {
        if (willCollide(wallSprite)) {
            player.setPosition(player.previousPosition);
            return true;
        }
        return false;
    }

    /**
     * Checks to see if player has collided with any of the goblin objects
     *
     * @return the goblin that the character collided with or null if none are present at position
     */
    private Goblin collidedWithGoblin() {
        return Arrays.stream(allGoblins).filter(gobs -> player.hasSamePosition(gobs.getPosition())).findFirst().orElse(null);
    }

    /**
     * checks all the goblins and sees if they're dead. If they are, reset their position to the maps default
     */
    private void isDead() {
        Arrays.stream(allGoblins).filter(gobs -> !gobs.getAlive()).forEach(gobs -> modifySpaceAtLocation(gobs.getPosition(), defaultSprite));
    }

    public boolean gameWinCheck() {
        return Arrays.stream(allGoblins).noneMatch(Goblin::getAlive);
    }

    private boolean willCollide(Sprite sprite) {
        return space[player.getPosition().getY()][player.getPosition().getY()] == sprite;
    }

    /**
     * updates the players' location with their sprite each time they move
     *
     * @param player Human player
     * @return returns land with player at current location
     */
    public Land withHuman(Human player) {
        this.player = player;
        modifySpaceAtLocation(player.getPosition(), player.sprite);
        return this;
    }

    /**
     * goes through each goblin and assigns them a location on the map if they're still alive
     *
     * @param goblin goes through each goblin and places them on the map if they are alive
     * @return returns land with goblins
     */
    public Land withGoblin(Goblin[] goblin) {
        this.allGoblins = goblin;
        Arrays.stream(allGoblins)
                .filter(Goblin::getAlive)
                .forEach(gob -> modifySpaceAtLocation(gob.getPosition(), gob.sprite));
        return this;
    }


    @Override
    public String toString() {
        return "Land{" + "maxColumns=" + Integer.toString(maxColumns) + ", maxRows=" + Integer.toString(maxRows) + "}";
    }

    public void renderSpace() {
        Render.render2DSpriteArray(space);
    }

    /**
     * Gets the positions for the outer bounds and then fills it with wall sprites
     *
     * @return returns Land with walls for rendering
     */
    public Land withWall() {
        Arrays.fill(space[0], wallSprite);
        Arrays.fill(space[maxColumns - 1], wallSprite);

        Arrays.stream(space).forEach(spriteArr -> {
            spriteArr[0] = wallSprite;
            spriteArr[maxRows - 1] = wallSprite;
        });
        return this;
    }
}
