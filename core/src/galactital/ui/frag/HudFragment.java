package galactital.ui.frag;

import arc.*;
import arc.scene.ui.layout.*;
import galactital.*;
import galactital.game.EventTypes.*;
import galactital.game.Inventory.*;
import galactital.ui.*;

public class HudFragment extends Fragment {
    private Table cont;

    @Override
    public void build(WidgetGroup parent) {
        //menu in the top left
        parent.fill(cont -> {
            cont.name = "thing";
            cont.top().left();
            cont.table(t -> t.label(() -> "FPS: " + Core.graphics.getFramesPerSecond())).top().left();
        });

        parent.fill(s -> {
            s.top();
            s.label(() -> Global.control.saveHandler.saving() ? "Saving..." : "");
        });

        //inventory
        parent.fill(inv -> {
            inv.name = "inventory";
            inv.bottom();
            cont = inv;
            rebuildInventory();
        });

        Events.run(InventoryUpdateEvent.class, this::rebuildInventory);
    }

    void rebuildInventory() {
        if (Global.player == null) return;
        cont.clear();

        cont.table(t -> {
            for (InventorySlot slot : Global.player.inventory.slots) {
                t.table(slot == Global.player.inventory.selected() ? UIStyles.whiteBordered : UIStyles.blackBordered, slotTable -> {
                    if (slot.isEmpty()) return;
                    slotTable.image(slot.type().region);
                    slotTable.label(() -> "" + slot.amount()).bottom().right().marginBottom(10).marginRight(10);
                }).width(64).height(64);
            }
        }).marginBottom(10);
    }
}
