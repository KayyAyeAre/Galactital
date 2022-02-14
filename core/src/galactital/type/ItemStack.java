package galactital.type;

public class ItemStack {
    public Item item;
    public int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public static ItemStack[] with(Object... items) {
        ItemStack[] out = new ItemStack[items.length / 2];
        for (int i = 0; i < items.length; i += 2) {
            out[i / 2] = new ItemStack((Item) items[i], (int) items[i + 1]);
        }
        return out;
    };
}
