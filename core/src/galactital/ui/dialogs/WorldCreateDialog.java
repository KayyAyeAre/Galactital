package galactital.ui.dialogs;

import arc.scene.ui.*;
import galactital.*;

public class WorldCreateDialog extends BaseDialog {
    public WorldCreateDialog() {
        super("Create New World");
        addCloseButton();
        TextField field = cont.add(new TextField("New World")).get();
        cont.row();
        cont.button("Create", () -> {
            hide();
            Global.ui.playDialog.hide();
            Global.control.playSave(Global.control.saveHandler.addSave(field.getText()));
        });
    }
}
