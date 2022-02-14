package galactital;

import arc.*;
import arc.assets.*;
import arc.files.*;
import arc.util.*;
import galactital.content.type.*;
import galactital.entity.*;
import galactital.game.*;
import galactital.graphics.*;
import galactital.input.*;
import galactital.ui.*;

import java.util.*;

public class Global implements Loadable {
    public static Spacecraft spacecraft;
    public static Player player;
    public static Renderer renderer;
    public static GameState state;
    public static Control control;
    public static ContentLoader content;
    public static UI ui;
    public static Fi dataDirectory;
    public static Fi saveDirectory;

    @Override
    public void loadAsync() {
        loadSettings();
        load();
    }

    public static void load() {
        dataDirectory = Core.settings.getDataDirectory();
        saveDirectory = dataDirectory.child("saves/");

        state = new GameState();
        content = new ContentLoader();
    }

    public static void loadSettings() {
        Core.settings.setAppName("Galactital");
        Core.keybinds.setDefaults(Binding.values());

        Core.bundle = I18NBundle.createBundle(Core.files.internal("bundles/bundle"), Locale.getDefault());
    }
}
