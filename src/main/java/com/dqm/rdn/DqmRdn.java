package com.dqm.rdn;

import com.dqm.rdn.capability.PlayerDataCapability;
import com.dqm.rdn.party.PartyTestCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.dqm.rdn.gold.GoldDropHandler;

@Mod("dqmrdn")
public class DqmRdn {

    public DqmRdn() {

        FMLJavaModLoadingContext.get()
                .getModEventBus()
                .addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(GoldDropHandler.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PlayerDataCapability.register();
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        PartyTestCommand.register(event.getDispatcher());
    }
}