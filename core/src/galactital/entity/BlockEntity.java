package galactital.entity;

import galactital.game.*;
import galactital.world.*;

public class BlockEntity extends Entity {
    public float x;
    public float y;
    public Block type;
    public Tile tile;
    public final Spacecraft parent;

    public BlockEntity(float x, float y, Block type, Tile tile, Spacecraft parent) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.parent = parent;
        this.tile = tile;
        Groups.block.add(this);
    }

    public void draw() {
        type.draw(this.tile);
    }

    @Override
    public void update() {}
}
