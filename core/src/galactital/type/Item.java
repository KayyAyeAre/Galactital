package galactital.type;

import galactital.content.type.*;

public class Item extends Content {
    public Item(String name) {
        super(name);
    }

    @Override
    public ContentType getContentType() {
        return ContentType.item;
    }
}
