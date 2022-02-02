package galactital.game;

import arc.*;
import arc.assets.*;
import arc.util.*;
import galactital.*;
import galactital.input.*;
import galactital.game.SaveHandler.*;
import galactital.game.GameState.*;

public class Control implements ApplicationListener, Loadable {
    public SaveHandler saveHandler;
    public InputHandler input;

    public Control() {
        saveHandler = new SaveHandler();
    }

    @Override
    public void update() {
        Groups.update();
        saveHandler.update();
        if (input != null) {
            input.update();
        }
    }

    @Override
    public void dispose() {
        if (saveHandler.getCurrent() != null) {
            saveHandler.getCurrent().save();
            Log.info("Saved on exit.");
        }
    }

    public void playSave(SaveSlot save) {
        Global.state.setState(State.playing);
        save.load();
    }

    @Override
    public void loadAsync() {
        saveHandler.load();
        input = new DesktopInput();
    }
}
