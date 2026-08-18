package com.dqm.rdn.gold;

import net.minecraft.entity.Entity;

public class DimensionGoldValues {

    public static double getMultiplier(Entity entity) {

        if (entity == null) {
            return 1.0;
        }

        if (entity.level.dimension() == net.minecraft.world.World.NETHER) {
            return 20.0;
        }

        if (entity.level.dimension() == net.minecraft.world.World.END) {
            return 40.0;
        }

        // オーバーワールド
        return 1.0;
    }
}