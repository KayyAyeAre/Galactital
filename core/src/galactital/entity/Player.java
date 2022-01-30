package galactital.entity;

import arc.graphics.g2d.*;
import arc.math.geom.*;
import galactital.*;
import galactital.game.*;

public class Player extends Entity implements Position {
    public float x, y;
    public float drawx, drawy;

    public Player() {
        Groups.player.add(this);
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
}
