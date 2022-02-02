package galactital.game;

import arc.*;
import arc.files.*;
import arc.graphics.*;
import arc.struct.*;
import arc.util.*;
import galactital.*;
import galactital.io.*;

import static galactital.Global.saveDirectory;

public class SaveHandler {
    Seq<SaveSlot> saves = new Seq<>();
    private @Nullable SaveSlot current;
    private float saveCounter;
    private boolean saving;

    public void load() {
        saves.clear();
        for (Fi file : saveDirectory.list()) {
            if (file.extension().equals("glvl")) saves.add(new SaveSlot(file));
        }
    }

    public void update() {
        if (Global.state.isPlaying() && current != null) {
            saveCounter += Time.delta;
            if (saveCounter > 30 * 60) {
                saving = true;
                current.save();
                saving = false;
                saveCounter = 0;
            }
        } else {
            saveCounter = 0;
        }
    }

    public Seq<SaveSlot> getSaves() {
        return saves;
    }

    public @Nullable SaveSlot getCurrent() {
        return current;
    }

    public boolean saving() {
        return saving;
    }

    public SaveSlot addSave(String name) {
        SaveSlot slot = new SaveSlot(getNextAvailable(name));
        saves.add(slot);
        slot.save();
        return slot;
    }

    public Fi getNextAvailable(String name) {
        int i = 0;
        Fi file;
        while ((file = saveDirectory.child(i + "-" + name + ".glvl")).exists()) {
            i++;
        }
        return file;
    }

    public class SaveSlot {
        public String name;
        public final Fi file;
        private Texture previewTexture;

        public SaveSlot(Fi file) {
            this.file = file;
            //should probably replace this with something else
            name = file.nameWithoutExtension().replaceFirst("[^-]*", "").replace("-", "");
        }

        public void save() {
            SaveIO.write(file);
            savePreview();
        }

        public Texture previewTexture() {
            if (!previewFile().exists()) return null;
            if (previewTexture == null) {
                previewTexture = new Texture(previewFile());
            }
            return previewTexture;
        }

        private Fi previewFile() {
            return file.sibling(file.nameWithoutExtension() + ".png");
        }

        public void savePreview() {
            ScreenUtils.saveScreenshot(previewFile(), Core.graphics.getWidth() / 2 - 256, Core.graphics.getHeight() / 2 - 256, 512, 512);
        }

        public void load() {
            SaveIO.read(file);
            savePreview();
            current = this;
        }

        public void delete() {
            if (current == this) current = null;
            file.delete();
            saves.remove(this);
        }
    }
}
