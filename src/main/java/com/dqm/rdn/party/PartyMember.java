package com.dqm.rdn.party;

import java.util.UUID;

public class PartyMember {

    public enum Type {
        PLAYER,
        MONSTER
    }

    private final Type type;
    private final UUID uuid;

    public PartyMember(Type type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;
    }

    public Type getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isPlayer() {
        return type == Type.PLAYER;
    }

    public boolean isMonster() {
        return type == Type.MONSTER;
    }
}