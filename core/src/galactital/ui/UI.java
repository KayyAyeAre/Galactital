package galactital.ui;

import arc.*;
import arc.assets.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import galactital.*;
import galactital.gen.*;
import galactital.ui.dialogs.*;
import galactital.ui.frag.*;

public class UI implements ApplicationListener, Loadable {
    public WidgetGroup menuGroup, hudGroup;

    public MenuFragment menufrag;
    public HudFragment hudfrag;

    public PlayDialog playDialog;
    public PauseDialog pauseDialog;
    public SettingsDialog settingsDialog;

    public UI() {
        Fonts.load();
    }

    @Override
    public void loadSync() {
        Fonts.def.getData().markupEnabled = true;

        Core.scene = new Scene();
        Core.input.addProcessor(Core.scene);

        UIUtils.load();
        UIStyles.load();
        UIUtils.loadStyles();
        load();
    }

    public void load() {
        menuGroup = new WidgetGroup();
        menuGroup.setFillParent(true);
        menuGroup.touchable = Touchable.childrenOnly;
        menuGroup.visible(() -> Global.state.isMenu());
        hudGroup = new WidgetGroup();
        hudGroup.setFillParent(true);
        hudGroup.touchable = Touchable.childrenOnly;
        hudGroup.visible(() -> Global.state.isGame());
        Core.scene.add(menuGroup);
        Core.scene.add(hudGroup);

        playDialog = new PlayDialog();
        pauseDialog = new PauseDialog();
        settingsDialog = new SettingsDialog();

        menufrag = new MenuFragment();
        hudfrag = new HudFragment();

        menufrag.build(menuGroup);
        hudfrag.build(hudGroup);
    }

    @Override
    public void update() {
        Core.scene.act();
        Core.scene.draw();
    }
}
