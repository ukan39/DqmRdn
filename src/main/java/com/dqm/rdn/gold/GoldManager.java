package com.dqm.rdn.gold;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class GoldManager {

    public static int calculateGold(PlayerEntity player, Entity defeatedEntity) {

        if (player == null || defeatedEntity == null) {
            return 0;
        }

        if (!(defeatedEntity instanceof LivingEntity)) {
            return 0;
        }

        LivingEntity livingEntity = (LivingEntity) defeatedEntity;

        int baseGold = MonsterGoldValues.getBaseGold(livingEntity);

        double multiplier =
                DimensionGoldValues.getMultiplier(defeatedEntity);

        return (int) (baseGold * multiplier);
    }
}