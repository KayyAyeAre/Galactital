package galactital.ui.dialogs;

import galactital.*;
import galactital.game.GameState.*;

public class PauseDialog extends BaseDialog {
    public PauseDialog() {
        super("@menu");

        shouldPause = true;
        shown(this::rebuild);
        addCloseListener();
    }

    private void rebuild() {
        cont.clear();

        update(() -> {
            if (Global.state.isMenu() && isShown()) hide();
        });

        cont.button("@back", this::hide).width(120);
        cont.button("@settings", Global.ui.settingsDialog::show).width(120);
        cont.button("@menu.exit", () -> {
            Global.state.setState(State.menu);
            hide();
        }).width(120);
    }
}
