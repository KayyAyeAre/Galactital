package galactital.ui.dialogs;

import arc.scene.ui.*;
import galactital.*;

public class WorldCreateDialog extends BaseDialog {
    public WorldCreateDialog() {
        super("@play.createworld");
        addCloseButton();
        TextField field = cont.add(new TextField("New World")).get();
        cont.row();
        cont.button("@play.createworld.create", () -> {
            hide();
            Global.ui.playDialog.hide();
            Global.control.playSave(Global.control.saveHandler.addSave(field.getText()));
        });
    }
}
