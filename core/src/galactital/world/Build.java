package galactital.world;

import galactital.*;
import galactital.content.*;

public class Build {
    public static void build(int x, int y, Block block) {
        if (!validPlace(x, y, block)) return;
        Tile tile = Global.spacecraft.get(x, y);
        if (tile == null) return;

        if (block instanceof Floor) tile.setFloor((Floor) block);
        else tile.setBlock(block);
    }

    public static void destroy(int x, int y) {
        Global.spacecraft.get(x, y).setBlock(Blocks.air);
    }

    public static boolean validPlace(int x, int y, Block block) {
        int offset = -(block.size - 1) / 2;

        for (int dx = 0; dx < block.size; dx++) {
            for (int dy = 0; dy < block.size; dy++) {
                int worldx = dx + offset + x;
                int worldy = dy + offset + y;

                Tile check = Global.spacecraft.get(worldx, worldy);
                if (!check.isEmpty()) return false;
            }
        }
        return true;
    }
}
