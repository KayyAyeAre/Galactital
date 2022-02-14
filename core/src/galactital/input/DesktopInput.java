package galactital.input;

import arc.*;
import arc.math.*;
import arc.util.*;
import galactital.entity.*;
import galactital.world.*;

import static galactital.Global.*;

public class DesktopInput extends InputHandler {
    private int index;
    private @Nullable Block block;

    @Override
    public void update() {
        int selectedX = (int) (spacecraft.x + Angles.trnsx(renderer.rotOffset, Core.input.mouseWorldX(), Core.input.mouseWorldY()) / 16);
        int selectedY = (int) (spacecraft.y + Angles.trnsy(renderer.rotOffset, Core.input.mouseWorldX(), Core.input.mouseWorldY()) / 16);
        if (state.isMenu()) return;

        float speed = 2 * Time.delta;
        player.x += Core.input.axis(Binding.move_x) * speed;
        player.y += Core.input.axis(Binding.move_y) * speed;

        index += Core.input.axisTap(Binding.hotbar_scroll);
        player.inventory.select(index);
        if (player.inventory.selected().type() instanceof Block) block = (Block) player.inventory.selected().type();
        if (block != null) block.drawPlace(selectedX, selectedY);

        if (Core.input.keyTap(Binding.select)) {
            BlockEntity entity = spacecraft.get(selectedX, selectedY).entity;
            if (entity != null) {
                frag.blocks.showConfig(entity);
            } else if (block != null) {
                Build.build(selectedX, selectedY, block);
            }
        }
        if (Core.input.keyTap(Binding.deconstruct)) Build.destroy(selectedX, selectedY);

    }
}
