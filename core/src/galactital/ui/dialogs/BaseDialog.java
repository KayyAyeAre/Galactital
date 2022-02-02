package galactital.ui.dialogs;

import arc.*;
import arc.scene.ui.*;
import arc.util.*;

public class BaseDialog extends Dialog {
    public BaseDialog(String title, DialogStyle style) {
        super(title, style);
        setFillParent(true);
        this.title.setAlignment(Align.center);
    }

    public BaseDialog(String title) {
        this(title, Core.scene.getStyle(DialogStyle.class));
    }

    @Override
    public void addCloseButton() {
        buttons.defaults().size(210f, 64f);
        buttons.button("Back", this::hide).size(210f, 64f);
        closeOnBack();
    }
}
