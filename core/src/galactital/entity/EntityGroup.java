package galactital.entity;

import arc.struct.*;

public class EntityGroup<T extends Entity> extends Seq<T> {
    public void update() {
        each(Entity::update);
    }
}
