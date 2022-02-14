package galactital.game;

import arc.*;
import arc.math.*;
import arc.util.io.*;
import galactital.*;
import galactital.content.*;
import galactital.content.type.*;
import galactital.game.EventTypes.*;

public class Inventory {
    public InventorySlot[] slots = new InventorySlot[16];
    protected InventorySlot selected;

    public Inventory() {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new InventorySlot(i);
        }
        selected = slots[0];

        add(Blocks.metalFloor, 5);
        add(Blocks.fuelTank, 5);
        add(Blocks.basicCrafter, 10);
    }

    public InventorySlot selected() {
        return selected;
    }

    public void select(int index) {
        selected = slots[Mathf.mod(index, slots.length)];
        Events.fire(new InventoryUpdateEvent());
    }

    public void add(Content type, int amount) {
        int toAdd = amount;
        for (int i = selected.index; i < slots.length + selected.index; i++) {
            InventorySlot slot = slots[i % slots.length];
            if (slot.isEmpty()) slot.type = type;
            if (!slot.canAdd(type)) continue;

            int space = slot.availableSpace();
            slot.amount += Math.min(toAdd, space);
            toAdd -= space;
            if (toAdd < 0) break;
        }
        Events.fire(new InventoryUpdateEvent());
    }

    public void write(Writes write) {
        write.i(slots.length);
        for (InventorySlot slot : slots) {
            write.i(slot.amount);
            write.i(slot.type == null ? -1 : slot.type.getContentType().ordinal());
            write.i(slot.type == null ? -1 : slot.type.id);
        }
    }

    public void read(Reads read) {
        int length = read.i();
        for (int i = 0; i < length; i++) {
            slots[i].amount = read.i();
            int typeid = read.i();
            int contentid = read.i();
            if (typeid != -1 && contentid != -1) slots[i].type = Global.content.getBy(ContentType.values()[typeid]).get(contentid);
        }
    }

    public static class InventorySlot {
        protected Content type;
        protected int amount;
        public final int index;

        public InventorySlot(int index) {
            this.index = index;
        }

        public boolean isEmpty() {
            return type == null || amount <= 0;
        }

        public int availableSpace() {
            return type == null ? 0 : type.maxStack - amount;
        }

        public boolean canAdd(Content type) {
            return (type == this.type && availableSpace() > 0) || isEmpty();
        }

        public Content type() {
            return type;
        }

        public int amount() {
            return amount;
        }
    }
}
