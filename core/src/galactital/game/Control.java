package galactital.game;

import arc.*;
import arc.assets.*;
import arc.input.*;
import arc.util.*;
import galactital.*;
import galactital.entity.*;
import galactital.game.EventTypes.*;
import galactital.game.GameState.*;
import galactital.game.SaveHandler.*;
import galactital.input.*;

public class Control implements ApplicationListener, Loadable {
    public SaveHandler saveHandler;
    public InputHandler input;

    public Control() {
        saveHandler = new SaveHandler();
    }

    public void playSave(SaveSlot save) {
        initWorld();
        save.load();
        Global.state.setState(State.playing);
    }

    public void initWorld() {
        Global.player = new Player();
        Global.spacecraft = new Spacecraft();
    }

    @Override
    public void dispose() {
        if (saveHandler.getCurrent() != null) {
            saveHandler.getCurrent().save();
            Log.info("Saved on exit.");
        }
    }

    @Override
    public void loadAsync() {
        saveHandler.load();
        input = new DesktopInput();
        Events.on(LoadFinishEvent.class, e -> input.add());
    }

    @Override
    public void update() {
        Groups.update();
        saveHandler.update();

        if (Global.state.isGame()) {
            input.update();
        }

        try {
            Core.assets.update();
        } catch (Exception ignored) {}

        if (Core.input.keyTap(KeyCode.escape)) {
            if (!Global.ui.pauseDialog.isShown() && !Core.scene.hasDialog() && Global.state.isGame()) {
                Global.ui.pauseDialog.show();
                Global.state.setState(State.paused);
            }
        }
    }
}
