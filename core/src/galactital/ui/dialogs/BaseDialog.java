package galactital.ui.dialogs;

import arc.*;
import arc.scene.ui.*;
import arc.util.*;
import galactital.*;
import galactital.game.GameState.*;

public class BaseDialog extends Dialog {
    protected boolean wasPaused;
    protected boolean shouldPause;

    public BaseDialog(String title, DialogStyle style) {
        super(title, style);
        setFillParent(true);
        this.title.setAlignment(Align.center);

        shown(() -> {
            if (shouldPause && Global.state.isGame()) {
                wasPaused = Global.state.isPaused();
                Global.state.setState(State.paused);
            }
        });

        hidden(() -> {
            if (shouldPause && Global.state.isGame() && !wasPaused) {
                Global.state.setState(State.playing);
            }
        });
    }

    public BaseDialog(String title) {
        this(title, Core.scene.getStyle(DialogStyle.class));
    }

    public void addCloseListener() {
        closeOnBack();
    }

    @Override
    public void addCloseButton() {
        buttons.defaults().size(210f, 64f);
        buttons.button("Back", this::hide).size(210f, 64f);

        addCloseListener();
    }
}
