package galactital.content.type;

import arc.struct.*;
import galactital.content.*;
import galactital.world.*;

@SuppressWarnings("unchecked")
public class ContentLoader {
    private Seq<Content>[] contentMap = new Seq[ContentType.values().length];

    public ContentLoader() {
        clear();
    }

    public void load() {
        Blocks.load();
    }

    public void clear() {
        contentMap = new Seq[ContentType.values().length];

        for (ContentType type : ContentType.values()) {
            contentMap[type.ordinal()] = new Seq<>();
        }
    }

    public void init() {
        for (ContentType type : ContentType.values()) {
            for (Content content : contentMap[type.ordinal()]) {
                content.init();
            }
        }
    }

    public void handleContent(Content content) {
        contentMap[content.getContentType().ordinal()].add(content);
    }

    public <T extends Content> Seq<T> getBy(ContentType type) {
        return (Seq<T>) contentMap[type.ordinal()];
    }

    public Seq<Block> blocks() {
        return getBy(ContentType.block);
    }
}
