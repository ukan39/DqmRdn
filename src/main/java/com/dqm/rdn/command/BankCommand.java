package com.dqm.rdn.command;

import com.dqm.rdn.capability.IPlayerData;
import com.dqm.rdn.capability.PlayerDataCapability;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dqmrdn")
public class BankCommand {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {

        // ==============================
        // Bank残高確認
        // /dqmbankcheck
        // ==============================
        event.getDispatcher().register(
                Commands.literal("dqmbankcheck")
                        .executes(context -> {

                            ServerPlayerEntity player =
                                    context.getSource().getPlayerOrException();

                            player.getCapability(
                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                            ).ifPresent(data -> {

                                player.sendMessage(
                                        new StringTextComponent(
                                                "銀行残高: " + data.getBankGold() + "G"
                                        ),
                                        player.getUUID()
                                );
                            });

                            return 1;
                        })
        );

        // ==============================
        // Bankへ預け入れ
        // /dqmbankdeposit <amount>
        // ==============================
        event.getDispatcher().register(
                Commands.literal("dqmbankdeposit")
                        .then(
                                Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )
                                        .executes(context -> {

                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );

                                            ServerPlayerEntity player =
                                                    context.getSource()
                                                            .getPlayerOrException();

                                            player.getCapability(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            ).ifPresent(data -> {

                                                if (!data.removeGold(amount)) {

                                                    player.sendMessage(
                                                            new StringTextComponent(
                                                                    "ゴールドが足りません。現在のゴールド: "
                                                                            + data.getGold() + "G"
                                                            ),
                                                            player.getUUID()
                                                    );

                                                    return;
                                                }

                                                data.addBankGold(amount);

                                                player.sendMessage(
                                                        new StringTextComponent(
                                                                amount + "Gを銀行に預けました。"
                                                                        + "銀行残高: "
                                                                        + data.getBankGold() + "G"
                                                        ),
                                                        player.getUUID()
                                                );
                                            });

                                            return 1;
                                        })
                        )
        );

        // ==============================
        // Bankから引き出し
        // /dqmbankwithdraw <amount>
        // ==============================
        event.getDispatcher().register(
                Commands.literal("dqmbankwithdraw")
                        .then(
                                Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )
                                        .executes(context -> {

                                            int amount =
                                                    IntegerArgumentType.getInteger(
                                                            context,
                                                            "amount"
                                                    );

                                            ServerPlayerEntity player =
                                                    context.getSource()
                                                            .getPlayerOrException();

                                            player.getCapability(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            ).ifPresent(data -> {

                                                if (!data.removeBankGold(amount)) {

                                                    player.sendMessage(
                                                            new StringTextComponent(
                                                                    "銀行残高が足りません。現在の銀行残高: "
                                                                            + data.getBankGold() + "G"
                                                            ),
                                                            player.getUUID()
                                                    );

                                                    return;
                                                }

                                                data.addGold(amount);

                                                player.sendMessage(
                                                        new StringTextComponent(
                                                                amount + "Gを銀行から引き出しました。"
                                                                        + "銀行残高: "
                                                                        + data.getBankGold() + "G"
                                                        ),
                                                        player.getUUID()
                                                );
                                            });

                                            return 1;
                                        })
                        )
        );
    }
}