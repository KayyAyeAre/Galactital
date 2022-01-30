package galactital.content;

import galactital.world.*;
import galactital.world.blocks.*;
import galactital.world.blocks.control.*;

public class Blocks {
    public static Block air, metalFloor, fuelTank, helm;

    public static void load() {
        air = new AirBlock("air");

        metalFloor = new Floor("metal-floor");

        fuelTank = new Block("fuel-tank") {{
            size = 2;
        }};

        helm = new HelmBlock("helm");
    }
}
