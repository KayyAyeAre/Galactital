package galactital.graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import galactital.*;
import galactital.entity.*;
import galactital.game.*;

public class Renderer implements ApplicationListener {
    public float rotOffset;

    @Override
    public void update() {
        //rotation and other stuff
        rotOffset = Mathf.lerpDelta(rotOffset, Global.state.spacecraft ? 0 : -Global.spacecraft.rotation, 0.12f);
        Core.camera.position.lerpDelta(Global.state.spacecraft ? Global.spacecraft : Global.player, 0.12f).rotate(rotOffset);
        Core.camera.mat.rotate(rotOffset);

        //actual drawing
        Core.camera.update();
        Draw.reset();
        Core.graphics.clear(Color.rgb(0, 0, 5 + (int) Mathf.absin(65, 10)));
        Draw.proj(Core.camera);
        Groups.spacecraft.each(Spacecraft::draw);
        Groups.player.each(Player::draw);
        Draw.reset();
        Draw.flush();
    }

    @Override
    public void resize(int width, int height) {
        Core.camera.resize(width, height);
    }
}
