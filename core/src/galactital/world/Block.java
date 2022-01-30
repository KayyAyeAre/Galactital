package galactital.world;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import galactital.*;
import galactital.content.type.*;
import galactital.entity.*;

public class Block extends Content {
    public String name;
    public int size = 1;
    public float offset;
    public Func3<Integer, Integer, Tile, BlockEntity> buildType;
    public boolean hidden;

    public Block(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.block;
    }

    @Override
    public void init() {
        offset = ((size + 1) % 2) / 2f;
        buildType = (x, y, tile) -> new BlockEntity(x + offset, y + offset, this, tile, tile.parent);
    }

    public void draw(Tile tile) {
        if (tile.entity != null) {
            BlockEntity block = tile.entity;
            float x = Angles.trnsx(block.parent.rotation, block.x, block.y) * 16 + block.parent.x;
            float y = Angles.trnsy(block.parent.rotation, block.x, block.y) * 16 + block.parent.y;
            Draw.rect(region, x, y, 16 * size, 16 * size, block.parent.rotation);
        } else {
            float x = Angles.trnsx(tile.parent.rotation, tile.x, tile.y) * 16 + tile.parent.x;
            float y = Angles.trnsy(tile.parent.rotation, tile.x, tile.y) * 16 + tile.parent.y;
            Draw.rect(region, x, y, 16 * size, 16 * size, tile.parent.rotation);
        }
    }

    public void drawPlace(int x, int y) {
        float dx = Angles.trnsx(Global.spacecraft.rotation, x + offset, y + offset) * 16 + Global.spacecraft.x;
        float dy = Angles.trnsy(Global.spacecraft.rotation, x + offset, y + offset) * 16 + Global.spacecraft.y;

        if (!Build.validPlace(x, y, this)) Draw.color(Color.red);
        Draw.alpha(0.5f);
        Draw.rect(region, dx, dy, 16 * size, 16 * size, Global.spacecraft.rotation);
        Draw.alpha(1f);
        Draw.color();
    }
}
