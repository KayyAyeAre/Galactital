package galactital.type;

import arc.util.*;
import galactital.content.type.*;

public class ContentStack {
    protected @Nullable Content type;
    protected int amount;

    public boolean isEmpty() {
        return type == null || amount <= 0;
    }

    public int availableSpace() {
        return type.maxStack - amount;
    }

    public Content type() {
        return type;
    }

    public int amount() {
        return amount;
    }
}
