package com.dqm.rdn.event;

import com.dqm.rdn.capability.PlayerDataProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.Entity;

@Mod.EventBusSubscriber(modid = "dqmrdn")
public class PlayerCapabilityEvent {

    private static final ResourceLocation PLAYER_DATA =
            new ResourceLocation("dqmrdn", "player_data");

    @SubscribeEvent
    public static void onAttachCapabilities(
            AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof PlayerEntity) {

            System.out.println("★ PlayerDataProvider付与");

            event.addCapability(
                    PLAYER_DATA,
                    new PlayerDataProvider()
            );
        }
    }
}