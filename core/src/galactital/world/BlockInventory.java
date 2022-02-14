package galactital.world;

import arc.struct.*;
import galactital.*;
import galactital.content.type.*;

public class BlockInventory {
    public Seq<BlockInventoryStack> stacks = new Seq<>();
    public int capacity = 20;

    public void add(Content type, int amount) {
        if (!canAdd(type, amount)) return;
        BlockInventoryStack stack = stacks.find(s -> s.type == type);
        if (stack == null) {
            stacks.add(new BlockInventoryStack(type, amount));
        } else {
            stack.amount += amount;
        }
    }

    //temporary solution for taking items from this inventory, TODO replace
    public void take() {
        Content type = stacks.first().type;
        if (!canRemove(type, 1)) return;
        remove(type, 1);
        Global.player.inventory.add(type, 1);
    }

    public int get(Content type) {
        return stacks.find(s -> s.type == type).amount;
    }

    public void remove(Content type, int amount) {
        if (!canRemove(type, amount)) return;
        BlockInventoryStack stack = stacks.find(s -> s.type == type);
        stack.amount -= amount;
        if (stack.amount <= 0) stacks.remove(stack);
    }

    public boolean canAdd(Content type, int amount) {
        BlockInventoryStack check = stacks.find(s -> s.type == type);
        int cAmount = (check == null ? 0 : check.amount) - amount;
        return cAmount <= capacity;
    }

    public boolean canRemove(Content type, int amount) {
        BlockInventoryStack check = stacks.find(s -> s.type == type);
        int cAmount = (check == null ? 0 : check.amount) - amount;
        return cAmount >= 0;
    }

    public static class BlockInventoryStack {
        public Content type;
        public int amount;

        public BlockInventoryStack(Content type, int amount) {
            this.type = type;
            this.amount = amount;
        }
    }
}
