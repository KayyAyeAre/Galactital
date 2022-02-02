package galactital.ui.dialogs;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import galactital.*;
import galactital.game.SaveHandler.*;

public class PlayDialog extends BaseDialog {
    private ScrollPane pane;
    private Table slots;
    private WorldCreateDialog worldCreateDialog = new WorldCreateDialog();

    public PlayDialog() {
        super("Play");
        addCloseButton();
        setup();
        shown(this::setup);
    }

    private void setup() {
        cont.clear();
        slots = new Table();
        pane = new ScrollPane(slots);
        pane.setScrollingDisabled(true, false);
        TextureRegion def = Core.atlas.find("nopreview");

        slots.marginRight(20).marginLeft(20);
        for (SaveSlot slot : Global.control.saveHandler.getSaves()) {
            TextButton button = new TextButton("");
            button.getLabel().remove();
            button.getCells().remove(button.getLabelCell());

            button.image(def).update(image -> {
                TextureRegionDrawable draw = (TextureRegionDrawable) image.getDrawable();

                Texture texture = slot.previewTexture();
                if (draw.getRegion() == def && texture != null) {
                    draw.setRegion(new TextureRegion(texture));
                }
                image.setScaling(Scaling.fit);
            }).left().size(100).padRight(5);

            button.table(t -> {
                t.add(slot.name).left().width(600).wrap();
                t.row();
                t.add("[gray]Saved as " + slot.file.name()).left().width(600).wrap();
            }).grow().left();

            button.clicked(() -> {
                hide();
                Global.control.playSave(slot);
            });

            slots.add(button).width(750).height(160).growX().uniformX();
            slots.row();
        }
        slots.button("Create New World", worldCreateDialog::show).width(250);

        cont.add(pane);
    }
}
