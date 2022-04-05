package goblins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSpace {

    @Test
    void TestModifySpaceAtLocation() {
        Land l1 = new Land(2, 2);

        Assertions.assertEquals('$', l1.modifySpaceAtLocation(1, 1, '$').space[1][1]);
    }
}
