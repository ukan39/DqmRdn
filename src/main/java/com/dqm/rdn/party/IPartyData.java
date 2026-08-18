package com.dqm.rdn.party;

import java.util.List;
import java.util.UUID;

public interface IPartyData {

    UUID getLeader();

    void setLeader(UUID leader);

    List<PartyMember> getMembers();

    boolean addMember(PartyMember member);

    boolean removeMember(UUID uuid);

    boolean hasMember(UUID uuid);

    int getPartyGold();

    void addPartyGold(int amount);

    boolean removePartyGold(int amount);
}