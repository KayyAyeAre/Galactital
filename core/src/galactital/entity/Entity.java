package galactital.entity;

import arc.util.io.*;

public abstract class Entity {
    public abstract void update();

    public abstract void add();

    public void write(Writes write) {}
    public void read(Reads read) {}
}
