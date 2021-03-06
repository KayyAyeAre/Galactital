package galactital.ui.frag;

import arc.*;
import arc.scene.ui.layout.*;
import galactital.*;
import galactital.game.EventTypes.*;

public class MenuFragment extends Fragment {
    private Table container;

    @Override
    public void build(WidgetGroup parent) {
        parent.fill(c -> {
            container = c;
            c.name = "menu";

            rebuild();
            Events.run(ResizeEvent.class, this::rebuild);
        });
    }

    private void rebuild() {
        container.button("@play", Global.ui.playDialog::show).size(240, 60);
        container.button("@settings", Global.ui.settingsDialog::show).size(240, 60);
    }
}
