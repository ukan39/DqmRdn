package com.dqm.rdn.capability;

public interface IPlayerData {

    int getGold();

    void setGold(int gold);

    void addGold(int amount);

    boolean removeGold(int amount);

    boolean hasEnoughGold(int amount);

    int getSmallMedal();

    void setSmallMedal(int smallMedal);

    int getBankGold();

    void setBankGold(int bankGold);

    void addBankGold(int amount);

    boolean removeBankGold(int amount);

    boolean hasEnoughBankGold(int amount);
}