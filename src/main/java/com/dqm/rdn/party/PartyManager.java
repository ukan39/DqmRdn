package com.dqm.rdn.party;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyManager {

    private static final Map<UUID, PartyData> PARTIES = new HashMap<>();
    private static final Map<UUID, UUID> MEMBER_PARTIES = new HashMap<>();

    private PartyManager() {
    }

    /**
     * 新しいパーティを作成する
     */
    public static PartyData createParty(UUID leader) {

        if (leader == null) {
            return null;
        }

        if (isInParty(leader)) {
            return null;
        }

        UUID partyId = UUID.randomUUID();

        PartyData party = new PartyData();

        party.setLeader(leader);

        party.addMember(
                new PartyMember(
                        PartyMember.Type.PLAYER,
                        leader
                )
        );

        PARTIES.put(partyId, party);
        MEMBER_PARTIES.put(leader, partyId);

        return party;
    }

    /**
     * パーティにメンバーを加入させる
     */
    public static boolean joinParty(UUID partyId, PartyMember member) {

        if (partyId == null || member == null) {
            return false;
        }

        PartyData party = PARTIES.get(partyId);

        if (party == null) {
            return false;
        }

        UUID memberUuid = member.getUuid();

        if (memberUuid == null || isInParty(memberUuid)) {
            return false;
        }

        if (!party.addMember(member)) {
            return false;
        }

        MEMBER_PARTIES.put(memberUuid, partyId);

        return true;
    }

    /**
     * メンバーをパーティから脱退させる
     */
    public static boolean leaveParty(UUID memberUuid) {

        if (memberUuid == null) {
            return false;
        }

        UUID partyId = MEMBER_PARTIES.get(memberUuid);

        if (partyId == null) {
            return false;
        }

        PartyData party = PARTIES.get(partyId);

        if (party == null) {
            MEMBER_PARTIES.remove(memberUuid);
            return false;
        }

        party.removeMember(memberUuid);
        MEMBER_PARTIES.remove(memberUuid);

        return true;
    }

    /**
     * メンバーが所属しているパーティを取得する
     */
    public static PartyData getParty(UUID memberUuid) {

        if (memberUuid == null) {
            return null;
        }

        UUID partyId = MEMBER_PARTIES.get(memberUuid);

        if (partyId == null) {
            return null;
        }

        return PARTIES.get(partyId);
    }

    /**
     * メンバーがパーティに所属しているか
     */
    public static boolean isInParty(UUID memberUuid) {

        if (memberUuid == null) {
            return false;
        }

        return MEMBER_PARTIES.containsKey(memberUuid);
    }

    /**
     * メンバーが所属しているパーティIDを取得する
     */
    public static UUID getPartyId(UUID memberUuid) {

        if (memberUuid == null) {
            return null;
        }

        return MEMBER_PARTIES.get(memberUuid);
    }

    /**
     * パーティIDからパーティを取得する
     */
    public static PartyData getPartyById(UUID partyId) {

        if (partyId == null) {
            return null;
        }

        return PARTIES.get(partyId);
    }
}