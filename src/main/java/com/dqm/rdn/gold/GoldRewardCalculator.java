package com.rdn.dqm.gold;

public class GoldRewardCalculator {

    /**
     * ゴールド獲得量を計算する。
     *
     * @param baseGold バイオームによる基準ゴールド
     * @param raceTreasureMultiplier 種族財宝倍率
     * @param rarityMultiplier レア倍率
     * @param difficultyMultiplier 難易度倍率
     * @return 最終的な獲得ゴールド（最低1G）
     */
    public static int calculate(
            int baseGold,
            double raceTreasureMultiplier,
            double rarityMultiplier,
            double difficultyMultiplier) {

        double result = baseGold
                * raceTreasureMultiplier
                * rarityMultiplier
                * difficultyMultiplier;

        return Math.max(1, (int) Math.round(result));
    }
}