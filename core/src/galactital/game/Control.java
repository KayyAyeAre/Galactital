package galactital.game;

import arc.*;
import arc.assets.*;
import arc.util.*;
import galactital.input.*;

public class Control implements ApplicationListener, Loadable {
    public InputHandler input;

    @Override
    public void update() {
        Groups.update();
        if (input != null) {
            input.update();
        }
    }

    @Override
    public void loadAsync() {
        input = new DesktopInput();
    }
}
