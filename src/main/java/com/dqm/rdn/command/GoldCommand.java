package com.dqm.rdn.command;

import com.dqm.rdn.capability.PlayerDataCapability;
import com.dqm.rdn.capability.IPlayerData;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "dqmrdn")
public class GoldCommand {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {

        event.getDispatcher().register(
                Commands.literal("dqmgoldcheck")
                        .executes(context -> {
                            System.out.println("★ dqmgoldcheck 実行開始");
                            ServerPlayerEntity player =
                                    context.getSource().getPlayerOrException();

                            System.out.println("★ Capability取得前");
                            System.out.println(
                                    "★ GET Capability ID = " +
                                            System.identityHashCode(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            )
                            );
                            System.out.println(
                                    "★ GET player = " +
                                            player.getName().getString()
                            );
                            player.getCapability(
                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                            ).ifPresent(data -> {
                                System.out.println(
                                        "★ SET Capability ID = " +
                                                System.identityHashCode(
                                                        PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                                )
                                );
                                System.out.println("★ Capability取得成功");

                                player.sendMessage(
                                        new StringTextComponent(
                                                "ゴールド: " + data.getGold()
                                        ),
                                        player.getUUID()
                                );
                            });
                            return 1;
                        })
        );
        System.out.println("★ dqmgoldcheck 登録開始");
        event.getDispatcher().register(
                Commands.literal("dqmgoldset")
                        .then(
                                Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )
                                        .executes(context -> {

                                            int amount = IntegerArgumentType.getInteger(
                                                    context,
                                                    "amount"
                                            );
                                            ServerPlayerEntity player =
                                                    context.getSource().getPlayerOrException();
                                            System.out.println(
                                                    "★ SET player = " +
                                                            player.getName().getString()
                                            );
                                            player.getCapability(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            ).ifPresent(data -> {
                                                data.setGold(amount);
                                                System.out.println(
                                                        "★ SET Capability ID = " +
                                                                System.identityHashCode(
                                                                        PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                                                )
                                                );

                                                player.sendMessage(
                                                        new StringTextComponent(
                                                                "ゴールドを " + amount + " に設定しました"
                                                        ),
                                                        player.getUUID()
                                                );
                                            });
                                            context.getSource().sendSuccess(
                                                    new StringTextComponent(
                                                            "受け取った数字: " + amount
                                                    ),
                                                    false);

                                            return 1;
                                        })
                        )
        );
        event.getDispatcher().register(
                Commands.literal("dqmgoldadd")
                        .then(
                                Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )
                                        .executes(context -> {

                                            int amount = IntegerArgumentType.getInteger(
                                                    context,
                                                    "amount"
                                            );

                                            ServerPlayerEntity player =
                                                    context.getSource().getPlayerOrException();

                                            player.getCapability(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            ).ifPresent(data -> {

                                                int currentGold = data.getGold();

                                                data.addGold(amount);
                                                int newGold = data.getGold();

                                                player.sendMessage(
                                                        new StringTextComponent(
                                                                "ゴールドを " + amount +
                                                                        " 加算しました。現在のゴールド: " +
                                                                        newGold
                                                        ),
                                                        player.getUUID()
                                                );
                                            });

                                            return 1;
                                        })
                        )
        );
        event.getDispatcher().register(
                Commands.literal("dqmgoldremove")
                        .then(
                                Commands.argument(
                                                "amount",
                                                IntegerArgumentType.integer(0)
                                        )
                                        .executes(context -> {

                                            int amount = IntegerArgumentType.getInteger(
                                                    context,
                                                    "amount"
                                            );

                                            ServerPlayerEntity player =
                                                    context.getSource().getPlayerOrException();

                                            player.getCapability(
                                                    PlayerDataCapability.PLAYER_DATA_CAPABILITY
                                            ).ifPresent(data -> {

                                                int currentGold = data.getGold();

                                                if (!data.removeGold(amount)) {
                                                    player.sendMessage(
                                                            new StringTextComponent(
                                                                    "ゴールドが足りません。現在のゴールド: " +
                                                                            currentGold
                                                            ),
                                                            player.getUUID()
                                                    );
                                                    return;
                                                }

                                                int newGold = data.getGold();
                                                data.setGold(newGold);

                                                player.sendMessage(
                                                        new StringTextComponent(
                                                                "ゴールドを " + amount +
                                                                        " 減らしました。現在のゴールド: " +
                                                                        newGold
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