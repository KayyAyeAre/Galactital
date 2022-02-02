package galactital.entity;

import arc.func.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.io.*;
import galactital.*;
import galactital.game.*;
import galactital.world.*;

public class Spacecraft extends Entity implements Position {
    public float x;
    public float y;
    public float rotation;
    private TileCont tiles = new TileCont(500, 500, this);

    public Spacecraft(boolean add) {
        if (add) add();
    }

    public void draw() {
        tiles.each(Tile::draw);
    }

    public void add() {
        Groups.spacecraft.add(this);
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

    public void write(Writes write) {
        write.f(x);
        write.f(y);
        write.f(rotation);

        //write tiles
        write.i(tiles.width);
        write.i(tiles.height);

        for (int i = 0; i < tiles.width * tiles.height; i++) {
            int x = i % tiles.width, y = i / tiles.width;
            Tile t = tiles.getr(x, y);
            write.i(!t.isEmpty() && t.isCenter() ? t.entity.type.id : 0);
            write.i(t.floor() != null ? t.floor().id : 0);
        }
    }

    public void read(Reads read) {
        x = read.f();
        y = read.f();
        rotation = read.f();
        int width = read.i();
        int height = read.i();

        tiles = new TileCont(width, height, this);
        for (int i = 0; i < width * height; i++) {
            int x = i % width, y = i / width;
            Tile t = tiles.getr(x, y);
            t.setBlock(Global.content.block(read.i()));
            t.setFloor((Floor) Global.content.block(read.i()));
        }
    }
}
