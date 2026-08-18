package com.dqm.rdn.gold;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class BiomeGoldValues {

    public static int getBaseGold(Biome biome) {

        if (biome == null) {
            return 5;
        }

        RegistryKey<Biome> biomeKey =
                WorldGenRegistries.BIOME.getResourceKey(biome).orElse(null);

        if (biomeKey == null) {
            return 5;
        }

        // 平原
        if (biomeKey.equals(Biomes.PLAINS)
                || biomeKey.equals(Biomes.SUNFLOWER_PLAINS)) {
            return 5;
        }

        // 森林
        if (biomeKey.equals(Biomes.FOREST)
                || biomeKey.equals(Biomes.FLOWER_FOREST)
                || biomeKey.equals(Biomes.BIRCH_FOREST)
                || biomeKey.equals(Biomes.TALL_BIRCH_FOREST)
                || biomeKey.equals(Biomes.DARK_FOREST)
                || biomeKey.equals(Biomes.DARK_FOREST_HILLS)) {
            return 8;
        }

        // 海
        if (biomeKey.equals(Biomes.OCEAN)
                || biomeKey.equals(Biomes.DEEP_OCEAN)
                || biomeKey.equals(Biomes.COLD_OCEAN)
                || biomeKey.equals(Biomes.DEEP_COLD_OCEAN)
                || biomeKey.equals(Biomes.FROZEN_OCEAN)
                || biomeKey.equals(Biomes.DEEP_FROZEN_OCEAN)
                || biomeKey.equals(Biomes.LUKEWARM_OCEAN)
                || biomeKey.equals(Biomes.DEEP_LUKEWARM_OCEAN)
                || biomeKey.equals(Biomes.WARM_OCEAN)) {
            return 8;
        }

        // ジャングル
        if (biomeKey.equals(Biomes.JUNGLE)
                || biomeKey.equals(Biomes.JUNGLE_EDGE)
                || biomeKey.equals(Biomes.BAMBOO_JUNGLE)
                || biomeKey.equals(Biomes.BAMBOO_JUNGLE_HILLS)) {
            return 15;
        }

        // 山地
        if (biomeKey.equals(Biomes.MOUNTAINS)
                || biomeKey.equals(Biomes.MOUNTAIN_EDGE)
                || biomeKey.equals(Biomes.WOODED_MOUNTAINS)) {
            return 25;
        }

        // 砂漠
        if (biomeKey.equals(Biomes.DESERT)
                || biomeKey.equals(Biomes.DESERT_HILLS)
                || biomeKey.equals(Biomes.DESERT_LAKES)) {
            return 40;
        }

        // 雪山
        if (biomeKey.equals(Biomes.SNOWY_TUNDRA)
                || biomeKey.equals(Biomes.SNOWY_MOUNTAINS)
                || biomeKey.equals(Biomes.SNOWY_TAIGA)
                || biomeKey.equals(Biomes.SNOWY_TAIGA_HILLS)
                || biomeKey.equals(Biomes.SNOWY_TAIGA_MOUNTAINS)
                || biomeKey.equals(Biomes.ICE_SPIKES)) {
            return 60;
        }

        // ネザー
        if (biomeKey.equals(Biomes.NETHER_WASTES)
                || biomeKey.equals(Biomes.SOUL_SAND_VALLEY)
                || biomeKey.equals(Biomes.CRIMSON_FOREST)
                || biomeKey.equals(Biomes.WARPED_FOREST)
                || biomeKey.equals(Biomes.BASALT_DELTAS)) {
            return 100;
        }

        // エンド
        if (biomeKey.equals(Biomes.THE_END)
                || biomeKey.equals(Biomes.END_HIGHLANDS)
                || biomeKey.equals(Biomes.END_MIDLANDS)
                || biomeKey.equals(Biomes.SMALL_END_ISLANDS)
                || biomeKey.equals(Biomes.END_BARRENS)) {
            return 200;
        }

        return 5;
    }
}