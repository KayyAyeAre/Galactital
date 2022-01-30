package galactital.ui;

import arc.*;
import arc.assets.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.layout.*;
import galactital.ui.frag.*;

public class UI implements ApplicationListener, Loadable {
    public WidgetGroup menuGroup, hudGroup;

    public HudFragment hudfrag;

    public UI() {
        Fonts.load();
    }

    @Override
    public void loadSync() {
        load();
    }

    public void load() {
        Core.scene = new Scene();
        UIStyles.load();
        menuGroup = new WidgetGroup();
        hudGroup = new WidgetGroup();
        hudGroup.setFillParent(true);
        hudGroup.touchable = Touchable.childrenOnly;
        Core.scene.add(hudGroup);

        hudfrag = new HudFragment();
        hudfrag.build(hudGroup);
    }

    @Override
    public void update() {
        Core.scene.act();
        Core.scene.draw();
    }
}
