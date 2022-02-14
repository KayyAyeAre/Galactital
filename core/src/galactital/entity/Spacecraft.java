package galactital.entity;

import arc.func.*;
import arc.math.geom.*;
import arc.struct.*;
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

    public Spacecraft() {
        add();
    }

    public void draw() {
        tiles.each(Tile::draw);
    }

    @Override
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

    @Override
    public void write(Writes write) {
        write.f(x);
        write.f(y);
        write.f(rotation);

        write.i(tiles.width);
        write.i(tiles.height);

        //much more efficient than just cycling through every tile and writing unnecessary data
        Seq<Tile> writeTiles = new Seq<>();
        tiles.each(t -> !t.isEmpty(), writeTiles::add);
        write.i(writeTiles.size);

        writeTiles.each(t -> {
            write.i(t.x);
            write.i(t.y);
            write.bool(t.isCenter());
            write.i(!t.isEmpty() && t.isCenter() ? t.entity.type.id : 0);
            write.i(t.floor() != null ? t.floor().id : 0);
            if (t.entity != null) t.entity.write(write);
        });
    }

    @Override
    public void read(Reads read) {
        Global.spacecraft = this;
        x = read.f();
        y = read.f();
        rotation = read.f();
        int width = read.i();
        int height = read.i();

        tiles = new TileCont(width, height, this);
        int limit = read.i();
        for (int i = 0; i < limit; i++) {
            int x = read.i();
            int y = read.i();
            Tile t = tiles.get(x, y);
            boolean center = read.bool();
            int blockID = read.i();
            if (center) t.setBlock(Global.content.block(blockID));
            t.setFloor((Floor) Global.content.block(read.i()));
            if (t.entity != null) t.entity.read(read);
        }
    }
}
