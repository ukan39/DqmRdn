package com.dqm.rdn.gold;

import com.dqm.rdn.capability.PlayerDataCapability;
import com.dqm.rdn.party.PartyData;
import com.dqm.rdn.party.PartyManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class GoldDropHandler {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        System.out.println(
                "[Dqm] LivingDeathEvent fired"
                        + event.getEntityLiving().getType()
        );

        Entity entity = event.getEntityLiving();

        // プレイヤー死亡時は処理しない
        if (entity instanceof PlayerEntity) {
            return;
        }

        // 今回は「プレイヤーが直接倒した場合」を対象にする
        if (!(event.getSource().getEntity() instanceof PlayerEntity)) {
            return;
        }

        Entity attacker = event.getSource().getEntity();

        if (!(attacker instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity player = (PlayerEntity) attacker;

        int gold = GoldManager.calculateGold(player, entity);

        UUID playerUuid = player.getUUID();

        System.out.println(
                "[Dqm] Killer UUID = " + playerUuid
        );
        PartyData party = PartyManager.getParty(playerUuid);

        System.out.println(
                "[Dqm] Party Check = " + party
        );
        System.out.println(
                "[Dqm] Damage Entity = "
                        + event.getSource().getEntity()
        );
        System.out.println(
                "[Dqm] LivingDeathEvent fired : "
                        + entity.getType()
        );
        // パーティ所属中
        if (party != null) {

            party.addPartyGold(gold);

            System.out.println(
                    "[Dqm] Enemy defeated! Gold = " + gold
                            + " / Party Gold = " + party.getPartyGold()
            );

            return;
        }

        // ソロ
        player.getCapability(
                PlayerDataCapability.PLAYER_DATA_CAPABILITY
        ).ifPresent(data -> {

            data.addGold(gold);

            System.out.println(
                    "[Dqm] Enemy defeated! Gold = " + gold
                            + " / Total Gold = " + data.getGold()
            );
        });
    }
}