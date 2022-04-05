// [] TODO: Everything must be objects
// [] TODO: Override toString() for each object
// [x] TODO: Create a grid for the game world
// [] TODO: Use UTF characters for the players, goblins, and land
// [] TODO: Make the game turn based with movement: N/E/S/W
// [] TODO: Combat system
// [] TODO: combat using math.random
// [] TODO: Extras (optional)
    // [] TODO: Human has inventory
    // [] TODO: Goblins have drops
    // [] TODO: stats can be modified by equipment
    // [] TODO: map gen random treasure chest after each round of combat
    // [] TODO: goblins pursue player
// [] TODO: add unit testing

package goblins;

public class Main {

    public static void main(String[] args) {
        Land landSpace = new Land(10, 10);
        landSpace.renderSpace();
    }
}