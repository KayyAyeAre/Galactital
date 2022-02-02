package galactital.input;

import arc.*;
import arc.input.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import galactital.*;
import galactital.world.*;

public class DesktopInput extends InputHandler {
    private int currentBlock;
    private final Seq<Block> buildBlocks;

    public DesktopInput() {
        buildBlocks = Global.content.blocks().copy().removeAll(b -> b.hidden);
    }

    @Override
    public void update() {
        int selectedX = (int) (Global.spacecraft.x + Angles.trnsx(Global.renderer.rotOffset, Core.input.mouseWorldX(), Core.input.mouseWorldY()) / 16);
        int selectedY = (int) (Global.spacecraft.y + Angles.trnsy(Global.renderer.rotOffset, Core.input.mouseWorldX(), Core.input.mouseWorldY()) / 16);
        if (Global.state.isMenu()) return;
        if (Core.input.keyTap(KeyCode.h)) currentBlock++;
        currentBlock %= buildBlocks.size;
        Block build = buildBlocks.get(currentBlock);

        float speed = 2 * Time.delta;
        if (Core.input.keyDown(KeyCode.w)) Global.player.y += speed;
        if (Core.input.keyDown(KeyCode.s)) Global.player.y -= speed;
        if (Core.input.keyDown(KeyCode.d)) Global.player.x += speed;
        if (Core.input.keyDown(KeyCode.a)) Global.player.x -= speed;
        build.drawPlace(selectedX, selectedY);

        if (Core.input.keyTap(KeyCode.mouseLeft)) Build.build(selectedX, selectedY, build);
        if (Core.input.keyTap(KeyCode.mouseRight)) Build.destroy(selectedX, selectedY);
    }
}
