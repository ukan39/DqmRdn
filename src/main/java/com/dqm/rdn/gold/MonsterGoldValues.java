package com.dqm.rdn.gold;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

public class MonsterGoldValues {

    public static int getBaseGold(LivingEntity entity) {

        if (entity == null) {
            return 0;
        }

        EntityType<?> type = entity.getType();

        // スライム系
        if (type == EntityType.SLIME) {
            return 5;
        }

        // ゾンビ
        if (type == EntityType.ZOMBIE) {
            return 8;
        }

        // スケルトン
        if (type == EntityType.SKELETON) {
            return 10;
        }

        // クリーパー
        if (type == EntityType.CREEPER) {
            return 15;
        }

        // エンダーマン
        if (type == EntityType.ENDERMAN) {
            return 30;
        }

        // ガスト
        if (type == EntityType.GHAST) {
            return 40;
        }

        // ブレイズ
        if (type == EntityType.BLAZE) {
            return 50;
        }

        // 通常モンスターのデフォルト値
        return 5;
    }
}