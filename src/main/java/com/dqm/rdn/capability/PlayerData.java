package com.dqm.rdn.capability;

public class PlayerData implements IPlayerData {

    private int gold;
    private int smallMedal;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;

    }
    @Override
    public void addGold(int amount) {
        gold += amount;
    }

    @Override
    public boolean removeGold(int amount) {
        if (!hasEnoughGold(amount)) {
            return false;
        }

        gold -= amount;
        return true;
    }

    @Override
    public boolean hasEnoughGold(int amount) {
        return gold >= amount;
    }
    public int getSmallMedal() {
        return smallMedal;
    }

    public void setSmallMedal(int smallMedal) {
        this.smallMedal = smallMedal;
    }

    private int bankGold;

    @Override
    public int getBankGold() {
        return bankGold;
    }

    @Override
    public void setBankGold(int bankGold) {
        this.bankGold = bankGold;
    }

    @Override
    public void addBankGold(int amount) {
        if (amount > 0) {
            bankGold += amount;
        }
    }

    @Override
    public boolean removeBankGold(int amount) {
        if (!hasEnoughBankGold(amount)) {
            return false;
        }

        bankGold -= amount;
        return true;
    }

    @Override
    public boolean hasEnoughBankGold(int amount) {
        return bankGold >= amount;
    }
}