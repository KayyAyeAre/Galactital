package galactital.content.type;

import arc.*;
import arc.graphics.g2d.*;
import galactital.*;

public abstract class Content {
    public int maxStack = 32;
    public TextureRegion region;
    public Content(String name) {
        region = Core.atlas.find(name);
        Global.content.handleContent(this);
    }

    public void init() {}

    public abstract ContentType getContentType();
}
