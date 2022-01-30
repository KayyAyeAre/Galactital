package galactital.entity;

import arc.files.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.util.*;
import galactital.game.*;
import galactital.world.*;

public class Spacecraft extends Entity implements Position {
    public float x;
    public float y;
    public float rotation;
    private TileCont tiles = new TileCont(1000, 1000, this);

    public Spacecraft() {
        Groups.spacecraft.add(this);
    }

    public void draw() {
        tiles.each(Tile::draw);
    }

    @Nullable
    public Tile get(int x, int y) {
        return tiles.get(x, y);
    }

    public void each(Cons<Tile> cons) {
        tiles.each(cons);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void update() {}
}
