package com.dqm.rdn.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerDataStorage implements Capability.IStorage<IPlayerData> {

    @Override
    public INBT writeNBT(
            Capability<IPlayerData> capability,
            IPlayerData instance,
            Direction side) {

        CompoundNBT nbt = new CompoundNBT();

        nbt.putInt("Gold", instance.getGold());
        nbt.putInt("SmallMedal", instance.getSmallMedal());
        nbt.putInt("BankGold", instance.getBankGold());
        return nbt;
    }

    @Override
    public void readNBT(
            Capability<IPlayerData> capability,
            IPlayerData instance,
            Direction side,
            INBT nbt) {

        CompoundNBT compoundNBT = (CompoundNBT) nbt;

        instance.setGold(compoundNBT.getInt("Gold"));
        instance.setSmallMedal(compoundNBT.getInt("SmallMedal"));
    }
}