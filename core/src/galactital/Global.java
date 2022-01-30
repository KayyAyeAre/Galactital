package galactital;

import arc.*;
import arc.assets.*;
import arc.files.*;
import galactital.content.type.*;
import galactital.entity.*;
import galactital.game.*;
import galactital.graphics.*;
import galactital.ui.*;

public class Global implements Loadable {
    public static Spacecraft spacecraft;
    public static Player player;
    public static Renderer renderer;
    public static GameState state;
    public static Control control;
    public static ContentLoader content;
    public static Inventory inventory;
    public static UI ui;
    public static Fi dataDirectory;

    @Override
    public void loadAsync() {
        load();
    }

    public static void load() {
        dataDirectory = Core.settings.getDataDirectory();
        state = new GameState();
        content = new ContentLoader();
        inventory = new Inventory();

        spacecraft = new Spacecraft();
        player = new Player();
    }
}
