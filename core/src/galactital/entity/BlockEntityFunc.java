package galactital.entity;

import galactital.world.*;

// interface of blocks for creating their blockentity
public interface BlockEntityFunc {
    BlockEntity get(Tile tile);
}
