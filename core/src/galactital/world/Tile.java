package galactital.world;

import arc.util.*;
import galactital.entity.*;

public class Tile {
    public final Spacecraft parent;
    public int x;
    public int y;
    public @Nullable BlockEntity entity;
    protected @Nullable Floor floor;

    public Tile(int x, int y, Spacecraft parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public void draw() {
        if (isEmpty() && floor != null) floor.draw(this);
        if (!isEmpty() && isCenter()) {
            entity.draw();
        }
    }

    public boolean isEmpty() {
        return entity == null;
    }

    @Nullable
    public Tile nearby(int rotation) {
        if (rotation == 0) return parent.get(x + 1, y);
        if (rotation == 1) return parent.get(x, y + 1);
        if (rotation == 2) return parent.get(x - 1, y);
        if (rotation == 3) return parent.get(x, y - 1);
        return null;
    }

    public void setBlock(Block type) {
        entity = type.buildType.get(x, y, this);

        if (type.size > 1) {
            int offset = -(type.size - 1) / 2;

            for (int dx = 0; dx < type.size; dx++) {
                for (int dy = 0; dy < type.size; dy++) {
                    int worldx = dx + offset + x;
                    int worldy = dy + offset + y;
                    if (!(worldx == x && worldy == y)) parent.get(worldx, worldy).entity = entity;
                }
            }
        }
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Floor floor() {
        return floor;
    }

    public boolean isCenter() {
        return entity == null || entity.tile == this;
    }
}
