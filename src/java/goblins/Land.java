package goblins;

import goblins.utils.RenderUtils;

public class Land {

    // Member variables ------------------------------------------------------------------------------------------------

    private int maxColumns;
    private int maxRows;
    public char[][] space;

    // Constructor -----------------------------------------------------------------------------------------------------

    public Land(int desiredColumns, int desiredRows) {
        maxColumns = desiredColumns;
        maxRows = desiredRows;
        space = new char[maxColumns][maxRows];

        initLand();
    }

    // Components ------------------------------------------------------------------------------------------------------

    RenderUtils render = new RenderUtils();

    // Utility Methods -------------------------------------------------------------------------------------------------

    private char[][] initLand() {
        try {
            for (int i = 0; i < maxColumns; i++) {
                for (int j = 0; j < maxRows; j++) {
                    space[i][j] = '+';
                }
            }
        } catch (Exception e) {
            System.out.println("init section");
            e.printStackTrace();
        }

        return space;
    }

    public Land modifySpaceAtLocation(int column, int row, char sprite) {
        space[column][row] = sprite;
        return this;
    }

    // Rendering methods ----------------------------------------------

    public void renderSpace() {
        render.render2Darray(space);
    }
}
