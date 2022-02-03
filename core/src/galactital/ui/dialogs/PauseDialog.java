package galactital.ui.dialogs;

import galactital.*;
import galactital.game.GameState.*;

public class PauseDialog extends BaseDialog {
    public PauseDialog() {
        super("Menu");

        shouldPause = true;
        shown(this::rebuild);
        addCloseListener();
    }

    private void rebuild() {
        cont.clear();

        update(() -> {
            if (Global.state.isMenu() && isShown()) hide();
        });

        cont.button("Back", this::hide).width(120);
        cont.button("Exit to Menu", () -> {
            Global.state.setState(State.menu);
            hide();
        }).width(120);
    }
}
