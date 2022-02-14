package galactital.ui.dialogs;

import arc.*;

public class SettingsDialog extends BaseDialog {
    public SettingsDialog() {
        super("@settings");
        addCloseButton();
        shown(this::rebuild);
    }

    private void rebuild() {
        cont.check("Save Previews", b -> Core.settings.put("save-previews", b));
    }
}
