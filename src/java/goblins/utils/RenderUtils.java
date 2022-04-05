package goblins.utils;

public class RenderUtils {

    // Render methods --------------------------------------------------------------------------------------------------

    public void render2Darray(char[][] array) {
        try {
            for (int column = 0; column < array.length; column++) {
                for (int row = 0; row < array.length; row++) {
                    System.out.printf(" %c " , array[column][row]);
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Render2D area");
            e.printStackTrace();
        }
    }
}
