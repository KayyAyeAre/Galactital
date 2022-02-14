package galactital.world.blocks.production;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import galactital.content.*;
import galactital.content.type.*;
import galactital.entity.*;
import galactital.type.*;
import galactital.world.*;

public class Crafter extends Block {
    public Seq<CrafterPlan> plans;
    public TextureRegion topRegion;

    public Crafter(String name) {
        super(name);
        hasInventory = true;
        topRegion = Core.atlas.find(name + "-top");
        plans = Seq.with(new CrafterPlan(Blocks.fuelTank, ItemStack.with(), 60f));
    }

    @Override
    public void init() {
        super.init();
        buildType = tile -> new CrafterEntity(tile.x + offset, tile.y + offset, this, tile, tile.parent);
    }

    @Override
    public void draw(Tile tile) {
        CrafterEntity block = (CrafterEntity) tile.entity;
        float x = Angles.trnsx(block.parent.rotation, block.x, block.y) * 16 + block.parent.x;
        float y = Angles.trnsy(block.parent.rotation, block.x, block.y) * 16 + block.parent.y;
        Draw.rect(region, x, y, 16 * size, 16 * size, block.parent.rotation);
        Draw.alpha(block.progress / block.current.craftTime);
        Draw.rect(block.current.result.region, x, y, 14 * size, 14 * size, block.parent.rotation);
        Draw.alpha(1);
        Draw.rect(topRegion, x, y, 16 * size, 16 * size, block.parent.rotation + block.totalProgress);
    }

    public class CrafterEntity extends BlockEntity {
        public float progress;
        public float totalProgress;
        public CrafterPlan current = plans.first();

        public CrafterEntity(float x, float y, Block type, Tile tile, Spacecraft parent) {
            super(x, y, type, tile, parent);
            consumers = () -> current == null ? ItemStack.with() : current.requirements;
        }

        @Override
        public void update() {
            if (canCraft()) {
                progress += Time.delta;
                totalProgress += Time.delta;
                if (progress > current.craftTime) craft();
            }
        }

        public void craft() {
            consume();
            output(current.result);
            progress %= current.craftTime;
        }

        public boolean canCraft() {
            if (current == null) return false;
            boolean hasItems = true;
            for (ItemStack requirement : current.requirements) {
                if (requirement.amount < inventory.get(requirement.item)) hasItems = false;
            }
            return hasItems;
        }
    }

    public static class CrafterPlan {
        public Content result;
        public ItemStack[] requirements;
        public float craftTime;

        public CrafterPlan(Content result, ItemStack[] requirements, float craftTime) {
            this.result = result;
            this.requirements = requirements;
            this.craftTime = craftTime;
        }
    }
}
