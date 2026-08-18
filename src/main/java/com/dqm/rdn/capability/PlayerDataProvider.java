package com.dqm.rdn.capability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;

public class PlayerDataProvider implements ICapabilityProvider, INBTSerializable<INBT> {

    private final IPlayerData data = new PlayerData();

    private final LazyOptional<IPlayerData> optionalData =
            LazyOptional.of(() -> data);

    @Override
    public <T> LazyOptional<T> getCapability(
            Capability<T> cap,
            Direction side) {

        if (cap == PlayerDataCapability.PLAYER_DATA_CAPABILITY) {
            return optionalData.cast();
        }

        return LazyOptional.empty();
    }
    @Override
    public INBT serializeNBT() {
        return PlayerDataCapability.PLAYER_DATA_CAPABILITY
                .getStorage()
                .writeNBT(
                        PlayerDataCapability.PLAYER_DATA_CAPABILITY,
                        data,
                        null
                );
    }
    @Override
    public void deserializeNBT(INBT nbt) {
        PlayerDataCapability.PLAYER_DATA_CAPABILITY
                .getStorage()
                .readNBT(
                        PlayerDataCapability.PLAYER_DATA_CAPABILITY,
                        data,
                        null,
                        nbt
                );
    }
}