package org.abbas.simpleEvents;

import org.abbas.api.events.CustomPlayerJoinEvent;
import org.abbas.api.events.CustomPlayerQuitEvent;
import org.abbas.api.events.CustomProcessCommandEvent;
import org.abbas.api.events.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SimpleEvents extends JavaPlugin {

    private static SimpleEvents instance;

    public static SimpleEvents getInstance() {
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

    public static void registerListener(Listener listener) {
        if (instance != null) {
            Bukkit.getPluginManager().registerEvents(listener, instance);
        }
    }

    public static void callPlayerLevelUp(Player player, int newLevel) {
        Bukkit.getPluginManager().callEvent(new PlayerLevelUpEvent(player, newLevel));
    }

    public static void callCustomPlayerJoin(Player player, String message) {
        Bukkit.getPluginManager().callEvent(new CustomPlayerJoinEvent(player, message));
    }

    public static void callCustomPlayerQuit(Player player, String message) {
        Bukkit.getPluginManager().callEvent(new CustomPlayerQuitEvent(player, message));
    }

    public static void callCustomProcessCommand(Player player, String message, List<Command> commands) {
        Bukkit.getPluginManager().callEvent(new CustomProcessCommandEvent(player, message, commands));
    }
}
