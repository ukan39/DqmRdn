package com.dqm.rdn.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PlayerDataCapability {

    @CapabilityInject(IPlayerData.class)
    public static Capability<IPlayerData> PLAYER_DATA_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(
                IPlayerData.class,
                new PlayerDataStorage(),
                PlayerData::new
        );
    }
}