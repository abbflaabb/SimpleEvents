package org.abbas.simpleEvents;

import net.kyori.adventure.text.Component;
import org.abbas.api.events.*;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.damage.DeathMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public final class SimpleEvents extends JavaPlugin {

    private static @Nullable SimpleEvents instance;

    public static @Nullable SimpleEvents getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static void registerListener(@NonNull Listener listener) {
        if (instance == null) {
            throw new IllegalStateException("SimpleEvents is not enabled");
        }

        Bukkit.getPluginManager().registerEvents(listener, instance);
    }

    public static void callCustomPlayerLevelUp(
            @NonNull Player player,
            int newLevel
    ) {
        Bukkit.getPluginManager().callEvent(
                new PlayerLevelUpEvent(player, newLevel)
        );
    }

    public static void callCustomPlayerJoin(
            @NonNull Player player,
            @Nullable String message
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomPlayerJoinEvent(player, message)
        );
    }

    public static void callCustomPlayerQuit(
            @NonNull Player player,
            @Nullable String message
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomPlayerQuitEvent(player, message)
        );
    }

    public static void callCustomProcessCommand(
            @NonNull Player player,
            @NonNull String message,
            @NonNull List<Command> commands
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomProcessCommandEvent(
                        player,
                        message,
                        commands
                )
        );
    }

    public static void callCustomPlayerDeath(
            @NonNull Player player,
            @NonNull Component deathMessage,
            @NonNull DeathMessageType deathMessageType,
            @Nullable Player killer
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomPlayerDeathEvent(
                        player,
                        deathMessage,
                        deathMessageType,
                        killer
                )
        );
    }

    public static void callCustomBlockPlace(
            @NonNull Block block,
            @NonNull BlockState replacedBlockState,
            @NonNull ItemStack itemInHand,
            @NonNull Component message,
            @NonNull Player player
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomBlockPlaceEvent(
                        block,
                        replacedBlockState,
                        itemInHand,
                        message,
                        player
                )
        );
    }

    public static void callCustomBlockBreak(
            @NonNull Player player,
            @NonNull Block block,
            @NonNull Component message,
            boolean cancelled
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomBlockBreakEvent(
                        player,
                        message,
                        block,
                        cancelled
                )
        );
    }

    public static void callCustomPlayerChat(
            @NonNull Player player,
            @NonNull Component message,
            @NonNull String channelName,
            boolean cancelled
    ) {
        Bukkit.getPluginManager().callEvent(
                new CustomPlayerChatEvent(
                        player,
                        message,
                        channelName,
                        cancelled
                )
        );
    }
}