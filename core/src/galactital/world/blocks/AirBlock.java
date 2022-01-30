package galactital.world.blocks;

import galactital.world.*;

public class AirBlock extends Floor {
    public AirBlock(String name) {
        super(name);
        hidden = true;
        buildType = (x, y, tile) -> null;
    }

    @Override
    public void init() {}

    @Override
    public void draw(Tile tile) {}
}
