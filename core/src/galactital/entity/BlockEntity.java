package galactital.entity;

import arc.*;
import arc.func.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import galactital.content.type.*;
import galactital.game.*;
import galactital.type.*;
import galactital.ui.*;
import galactital.world.*;
import galactital.world.BlockInventory.*;

public class BlockEntity extends Entity {
    public float x;
    public float y;
    public Block type;
    public Tile tile;
    public Spacecraft parent;
    public Prov<ItemStack[]> consumers;

    public BlockInventory inventory;

    public BlockEntity(float x, float y, Block type, Tile tile, Spacecraft parent) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.parent = parent;
        this.tile = tile;

        if (type.hasInventory) inventory = new BlockInventory();

        add();
    }

    public void draw() {
        type.draw(tile);
    }

    public void output(Content type) {
        inventory.add(type, 1);
    }

    public void consume() {
        for (ItemStack stack : consumers.get()) {
            inventory.remove(stack.item, stack.amount);
        }
    }

    @Override
    public void update() {}

    public boolean configTapped() {
        return inventory != null || type.configurable;
    }

    public void buildOverlay(Table table) {
        if (type.configurable) buildConfiguration(table);
        if (inventory != null) buildInventory(table);
    }

    public void buildInventory(Table table) {
        table.table(UIStyles.blackBordered, t -> {
            int row = 0;
            for (BlockInventoryStack stack : inventory.stacks) {
                t.table(slot -> {
                    slot.image(stack.type.region);
                    slot.label(() -> "" + stack.amount).bottom().left();
                });
                if (row++ % 5 == 0) t.row();
            }
        });
    }

    public void buildConfiguration(Table table) {}

    public void updateTableAlign(Table table) {
        Vec2 to = new Vec2(x * 16 + (type.size * 8), y * 16 + (type.size * 8)).rotate(parent.rotation).add(parent);
        to = Core.input.mouseScreen(to.x, to.y);
        table.setPosition(to.x, to.y, Align.topLeft);
    }

    @Override
    public void add() {
        Groups.block.add(this);
    }
}
