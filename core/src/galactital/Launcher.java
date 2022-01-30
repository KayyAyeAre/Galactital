package galactital;

import arc.*;
import arc.assets.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.util.*;
import galactital.content.type.*;
import galactital.game.*;
import galactital.graphics.*;
import galactital.ui.*;

public abstract class Launcher extends ApplicationCore {
    private boolean loadFinish = false;
    private long beginTime;

    @Override
    public void setup() {
        beginTime = Time.millis();
        Core.settings.setAppName("Galactital");
        Core.assets = new AssetManager();
        Core.batch = new SortedSpriteBatch();
        Core.camera = new Camera();
        Core.atlas = TextureAtlas.blankAtlas();

        Core.assets.load(new Global());
        Core.assets.load(new AssetDescriptor<>("sprites/sprites.aatls", TextureAtlas.class)).loaded = t -> Core.atlas = t;
        Core.assets.loadRun("contentload", ContentLoader.class, () -> Global.content.load(), () -> Global.content.init());

        add(Global.renderer = new Renderer());
        add(Global.control = new Control());
        add(Global.ui = new UI());
    }

    @Override
    public void add(ApplicationListener module) {
        super.add(module);
        if (module instanceof Loadable) {
            Core.assets.load((Loadable) module);
        }
    }

    @Override
    public void update() {
        if (!loadFinish) {
            if (Core.assets.update()) {
                Log.info("Total loading time: @ms", Time.timeSinceMillis(beginTime));
                for (ApplicationListener listener : modules) {
                    listener.init();
                }
                loadFinish = true;
                resize(Core.graphics.getWidth(), Core.graphics.getHeight());
            }
        } else super.update();
    }

    @Override
    public void resize(int width, int height) {
        if (Core.assets == null) return;
        if (!loadFinish) {
            Draw.proj().setOrtho(0, 0, width, height);
        } else {
            super.resize(width, height);
        }
    }
}
