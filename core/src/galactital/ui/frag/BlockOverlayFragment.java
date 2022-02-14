package galactital.ui.frag;

import arc.*;
import arc.scene.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import galactital.*;
import galactital.content.*;
import galactital.entity.*;

public class BlockOverlayFragment extends Fragment {
    BlockEntity configEntity;
    Table table = new Table();

    @Override
    public void build(WidgetGroup parent) {
        table.visible = false;
        parent.addChild(table);

        Core.scene.add(new Element() {
            @Override
            public void act(float delta) {
                super.act(delta);
                if (Global.state.isMenu()) {
                    table.visible = false;
                    configEntity = null;
                }
            }
        });
    }

    public void showConfig(BlockEntity entity) {
        if (entity.configTapped()) {
            configEntity = entity;

            table.visible = true;
            table.clear();
            entity.buildOverlay(table);
            table.pack();
            table.setTransform(true);

            table.update(() -> {
                table.setOrigin(Align.center);

                if (configEntity == null || configEntity.type == Blocks.air) {
                    hideConfig();
                } else {
                    configEntity.updateTableAlign(table);
                }
            });
        }
    }

    public void hideConfig() {
        configEntity = null;
    }
}
