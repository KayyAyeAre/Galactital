package galactital.game;

import galactital.entity.*;

public class Groups {
    public static EntityGroup<Player> player = new EntityGroup<>();
    public static EntityGroup<Spacecraft> spacecraft = new EntityGroup<>();
    public static EntityGroup<BlockEntity> block = new EntityGroup<>();

    public static void update() {
        player.update();
        block.update();
        spacecraft.update();
    }
}
