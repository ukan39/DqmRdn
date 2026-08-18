package com.dqm.rdn.event;

import com.dqm.rdn.capability.PlayerDataCapability;
import com.dqm.rdn.capability.IPlayerData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dqmrdn")
public class PlayerGoldEvent {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        PlayerEntity original = event.getOriginal();
        PlayerEntity newPlayer = event.getPlayer();

        original.getCapability(
                PlayerDataCapability.PLAYER_DATA_CAPABILITY
        ).ifPresent(oldData -> {

            newPlayer.getCapability(
                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
            ).ifPresent(newData -> {

                int oldGold = oldData.getGold();
                int lostGold = 0;

                if (event.isWasDeath()) {

                    if (oldGold <= 50000) {
                        lostGold = oldGold / 2;
                    } else {
                        int firstPart = 50000 / 2;
                        int remainingGold = oldGold - 50000;
                        int secondPart = remainingGold * 3 / 10;

                        lostGold = firstPart + secondPart;
                    }
                }

                int newGold = oldGold - lostGold;

                newData.setGold(newGold);
                newData.setBankGold(oldData.getBankGold());

                newData.setGold(newGold);

                System.out.println(
                        "★ Gold引き継ぎ: " +
                                oldGold + "G → " +
                                newGold + "G（" +
                                lostGold + "Gロスト）"
                );
            });
        });
    }
}