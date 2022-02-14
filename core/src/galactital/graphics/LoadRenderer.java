package galactital.graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;

public class LoadRenderer {
    private float progress;

    public void draw() {
        progress = Mathf.lerpDelta(progress,(Core.graphics.getWidth() - 100) * Core.assets.getProgress(), 0.16f);
        Core.graphics.clear(Color.black);
        Draw.proj().setOrtho(0, 0, Core.graphics.getWidth(), Core.graphics.getHeight());
        Fill.light((float) Core.graphics.getWidth() / 2, (float) Core.graphics.getHeight() / 2, 20, Math.max(Core.graphics.getWidth(), Core.graphics.getHeight()) * 0.6f, Color.rgb(0, 0, 26), Color.clear);
        Draw.color(Color.gray);
        Fill.rect((float) Core.graphics.getWidth() / 2, (float) Core.graphics.getHeight() / 2, Core.graphics.getWidth() - 100, 40);
        Draw.color();
        Fill.rect((float) Core.graphics.getWidth() / 2, (float) Core.graphics.getHeight() / 2, progress, 40);
        Draw.flush();
    }
}
