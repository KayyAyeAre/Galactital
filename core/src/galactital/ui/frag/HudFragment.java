package galactital.ui.frag;

import arc.*;
import arc.scene.ui.layout.*;
import galactital.*;
import galactital.type.*;
import galactital.ui.*;

public class HudFragment extends Fragment {
    @Override
    public void build(WidgetGroup parent) {
        //menu in the top left
        parent.fill(cont -> {
            cont.name = "thing";
            cont.top().left();
            cont.table(t -> t.label(() -> "FPS: " + Core.graphics.getFramesPerSecond())).top().left();
        });
        //inventory
        parent.fill(inv -> {
            inv.name = "inventory";
            inv.bottom();
            inv.table(t -> {
                for (ContentStack stack : Global.inventory.stacks) {
                    t.table(UIStyles.black, slot -> {
                        if (stack.isEmpty()) return;
                        slot.image(stack.type().region);
                    });
                }
            });
        });
    }
}
