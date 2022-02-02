package galactital.world;

import arc.func.*;
import arc.util.*;
import galactital.content.*;
import galactital.entity.*;

public class TileCont {
    Tile[][] arr;
    public final int width, height;
    public final Spacecraft parent;

    public TileCont(int width, int height, Spacecraft parent) {
        arr = new Tile[width][height];
        this.width = width;
        this.height = height;
        this.parent = parent;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int realX = x - (width / 2);
                int realY = y - (height / 2);
                arr[x][y] = new Tile(realX, realY, parent);
            }
        }
    }

    @Nullable
    public Tile get(int x, int y) {
        int realX = x + (width / 2);
        int realY = y + (height / 2);
        return getr(realX, realY);
    }

    @Nullable
    public Tile getr(int x, int y) {
        return (x < 0 || x >= width || y < 0 || y >= height) ? null : arr[x][y];
    }

    public void each(Cons<Tile> cons) {
        for (Tile[] arr2 : arr) {
            for (Tile tile : arr2) {
                cons.get(tile);
            }
        }
    }
}
