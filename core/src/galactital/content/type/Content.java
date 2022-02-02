package galactital.content.type;

import arc.*;
import arc.graphics.g2d.*;
import galactital.*;

public abstract class Content {
    public int id;
    public int maxStack = 32;
    public TextureRegion region;

    public Content(String name) {
        id = Global.content.getBy(getContentType()).size;
        region = Core.atlas.find(name);
        Global.content.handleContent(this);
    }

    public void init() {}

    public abstract ContentType getContentType();
}
