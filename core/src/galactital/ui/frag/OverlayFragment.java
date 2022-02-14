package galactital.ui.frag;

import arc.scene.event.*;
import arc.scene.ui.layout.*;
import galactital.*;

public class OverlayFragment {
    public BlockOverlayFragment blocks;

    private WidgetGroup group = new WidgetGroup();

    public OverlayFragment() {
        group.touchable = Touchable.childrenOnly;
        blocks = new BlockOverlayFragment();
    }

    public void add() {
        group.setFillParent(true);
        Global.ui.hudGroup.addChild(group);

        blocks.build(group);
    }
}
