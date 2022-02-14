package galactital.entity;

import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.io.*;
import galactital.*;
import galactital.game.*;

public class Player extends Entity implements Position {
    public float x, y;
    public float drawx, drawy;
    public Inventory inventory;

    public Player() {
        inventory = new Inventory();
        add();
    }

    public void draw() {
        Fill.rect(drawx, drawy, 20, 20);
    }

    @Override
    public float getX() {
        return drawx;
    }

    @Override
    public float getY() {
        return drawy;
    }

    @Override
    public void update() {
        drawx = x + Global.spacecraft.x;
        drawy = y + Global.spacecraft.y;
    }

    @Override
    public void add() {
        Groups.player.add(this);
    }

    @Override
    public void write(Writes write) {
        write.f(x);
        write.f(y);
        inventory.write(write);
    }

    @Override
    public void read(Reads read) {
        Global.player = this;
        x = read.f();
        y = read.f();
        inventory.read(read);
    }
}
