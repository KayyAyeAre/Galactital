package galactital.input;

import arc.*;
import arc.input.*;
import galactital.ui.frag.*;

public abstract class InputHandler implements InputProcessor {
    public final OverlayFragment frag = new OverlayFragment();

    public void add() {
        Core.input.addProcessor(this);
        frag.add();
    }

    public abstract void update();
}
