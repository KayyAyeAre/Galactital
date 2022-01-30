package galactital.game;

import galactital.type.*;

public class Inventory {
    public ContentStack[] stacks = new ContentStack[16];

    public Inventory() {
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new ContentStack();
        }
    }

    //TODO adding/removing stuff to the inventory
}
