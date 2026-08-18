package com.dqm.rdn.party;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartyData implements IPartyData {

    private UUID leader;
    private final List<PartyMember> members = new ArrayList<>();
    private int partyGold;

    @Override
    public UUID getLeader() {
        return leader;
    }

    @Override
    public void setLeader(UUID leader) {
        this.leader = leader;
    }

    @Override
    public List<PartyMember> getMembers() {
        return members;
    }

    @Override
    public boolean addMember(PartyMember member) {

        if (member == null || hasMember(member.getUuid())) {
            return false;
        }

        members.add(member);
        return true;
    }

    @Override
    public boolean removeMember(UUID uuid) {

        if (uuid == null) {
            return false;
        }

        return members.removeIf(member ->
                uuid.equals(member.getUuid())
        );
    }

    @Override
    public boolean hasMember(UUID uuid) {

        if (uuid == null) {
            return false;
        }

        for (PartyMember member : members) {

            if (uuid.equals(member.getUuid())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getPartyGold() {
        return partyGold;
    }

    @Override
    public void addPartyGold(int amount) {

        if (amount > 0) {
            partyGold += amount;
        }
    }

    @Override
    public boolean removePartyGold(int amount) {

        if (amount < 0 || partyGold < amount) {
            return false;
        }

        partyGold -= amount;
        return true;
    }
}