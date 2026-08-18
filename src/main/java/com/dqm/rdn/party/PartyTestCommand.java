package com.dqm.rdn.party;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

public class PartyTestCommand {

    public static void register(
            CommandDispatcher<CommandSource> dispatcher) {

        dispatcher.register(
                Commands.literal("dqm")
                        .then(Commands.literal("partytest")
                                .executes(context -> {

                                    ServerPlayerEntity player =
                                            context.getSource().getPlayerOrException();

                                    UUID playerA =
                                            player.getUUID();

                                    UUID playerB =
                                            UUID.randomUUID();

                                    UUID monster =
                                            UUID.randomUUID();

                                    PartyData party =
                                            PartyManager.createParty(playerA);

                                    if (party == null) {
                                        player.sendMessage(
                                                new StringTextComponent(
                                                        "[Dqm] パーティ作成に失敗しました。"
                                                ),
                                                player.getUUID()
                                        );

                                        return 0;
                                    }

                                    UUID partyId =
                                            PartyManager.getPartyId(playerA);

                                    PartyManager.joinParty(
                                            partyId,
                                            new PartyMember(
                                                    PartyMember.Type.PLAYER,
                                                    playerB
                                            )
                                    );

                                    PartyManager.joinParty(
                                            partyId,
                                            new PartyMember(
                                                    PartyMember.Type.MONSTER,
                                                    monster
                                            )
                                    );

                                    party.addPartyGold(100);

                                    player.sendMessage(
                                            new StringTextComponent(
                                                    "[Dqm] Party Test OK"
                                            ),
                                            player.getUUID()
                                    );

                                    player.sendMessage(
                                            new StringTextComponent(
                                                    "[Dqm] Members = "
                                                            + party.getMembers().size()
                                            ),
                                            player.getUUID()
                                    );

                                    player.sendMessage(
                                            new StringTextComponent(
                                                    "[Dqm] Party Gold = "
                                                            + party.getPartyGold()
                                            ),
                                            player.getUUID()
                                    );

                                    return 1;
                                })
                        )
                        .then(Commands.literal("partygold")
                                .executes(context -> {

                                    ServerPlayerEntity player =
                                            context.getSource().getPlayerOrException();

                                    PartyData party =
                                            PartyManager.getParty(
                                                    player.getUUID()
                                            );

                                    if (party == null) {

                                        player.sendMessage(
                                                new StringTextComponent(
                                                        "[Dqm] パーティに所属していません。"
                                                ),
                                                player.getUUID()
                                        );

                                        return 0;
                                    }

                                    player.sendMessage(
                                            new StringTextComponent(
                                                    "[Dqm] Party Gold = "
                                                            + party.getPartyGold()
                                            ),
                                            player.getUUID()
                                    );

                                    return 1;
                                })
                        )
        );
    }
}