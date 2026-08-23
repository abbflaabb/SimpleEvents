# simpleEvents

A lightweight Spigot/Paper plugin that exposes a small API for firing custom Bukkit-style events from Java code.

[![](https://jitpack.io/v/abbflaabb/SimpleEvents.svg)](https://jitpack.io/#abbflaabb/SimpleEvents)

## Overview

`simpleEvents` is designed for Minecraft server plugins that need to trigger custom gameplay events without tightly coupling code to the Bukkit event system. It provides a lightweight bridge between vanilla Bukkit/Paper events and custom event classes for common scenarios such as:

- player join messages
- player quit messages
- death messages
- block break/place events
- chat events
- command processing events
- level-up events

The plugin automatically listens for common vanilla events and fires the matching custom `org.abbas.api.events.*` events, so dependent plugins can listen to the custom API directly without manually wiring every vanilla listener.

## Project structure

- `src/main/java/org/abbas/simpleEvents/SimpleEvents.java` - plugin entry point and event helper methods
- `src/main/java/org/abbas/api/events` - custom event classes
- `src/main/resources/plugin.yml` - Bukkit plugin metadata

## Build

```bash
mvn clean package
```

This produces a JAR in the `target/` directory.

## Installation

1. Add the JitPack repository to your plugin project.
2. Add the `simpleevents` dependency.
3. Build your plugin with Maven.
4. Copy the generated JAR into your server's `plugins/` folder.
5. Start the server.

### Maven dependency

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.abbas</groupId>
        <artifactId>simpleevents</artifactId>
        <version>1.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

If you are using a published release on JitPack, replace `1.1-SNAPSHOT` with the version you want to use.

## How to use

### Automatic event bridging

When the plugin is enabled, it registers an internal bridge that automatically fires the matching custom events for these vanilla/Paper events:

- `PlayerJoinEvent` -> `CustomPlayerJoinEvent`
- `PlayerQuitEvent` -> `CustomPlayerQuitEvent`
- `PlayerCommandPreprocessEvent` -> `CustomProcessCommandEvent`
- `PlayerDeathEvent` -> `CustomPlayerDeathEvent`
- `BlockBreakEvent` -> `CustomBlockBreakEvent`
- `BlockPlaceEvent` -> `CustomBlockPlaceEvent`
- `AsyncChatEvent` -> `CustomPlayerChatEvent`

This means you normally only need to listen for the custom events in your plugin.

### Example listener for a custom join event

```java
package org.abbas.pluginTestSimpleEvent.listeners;

import net.md_5.bungee.api.ChatColor;
import org.abbas.api.events.CustomPlayerJoinEvent;
import org.abbas.pluginTestSimpleEvent.PluginTestSimpleEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListeners implements Listener {

    private final PluginTestSimpleEvent plugin;

    public PlayerJoinListeners(PluginTestSimpleEvent plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVanillaJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(CustomPlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String msg = plugin.getConfig().getString("Messages.Join");

        if (msg == null || msg.isEmpty()) {
            plugin.getLogger().warning("Missing 'Messages.Join' in config");
            return;
        }

        String finalMessage = ChatColor.translateAlternateColorCodes('&', msg.replace("%player%", playerName));
        event.setMessage(finalMessage);
        plugin.getServer().broadcastMessage(finalMessage);
    }
}
```

Register the listener in your plugin:

```java
getServer().getPluginManager().registerEvents(new PlayerJoinListeners(this), this);
```

### Manually firing custom events

Some events (such as `PlayerLevelUpEvent`) do not have a direct vanilla Bukkit equivalent and must be fired manually:

```java
SimpleEvents.callCustomPlayerJoin(player, "Welcome back!");
SimpleEvents.callCustomPlayerQuit(player, "See you soon!");
SimpleEvents.callCustomPlayerLevelUp(player, 10);
```

### Command-processing event

```java
List<Command> commands = new ArrayList<>();
commands.add(new SomeCommand());
SimpleEvents.callCustomProcessCommand(player, "Processing command chain", commands);
```

## Available events

- `CustomPlayerJoinEvent`
  - `getPlayer()`
  - `getMessage()`
  - `setMessage(String)`

- `CustomPlayerQuitEvent`
  - `getPlayer()`
  - `getMessage()`
  - `setMessage(String)`

- `CustomPlayerDeathEvent`
  - `getPlayer()`
  - `getDeathMessage()`
  - `setDeathMessage(Component)`
  - `getDeathMessageType()`
  - `getKiller()`

- `CustomBlockBreakEvent`
  - `getPlayer()`
  - `getBlock()`
  - `getMessage()`
  - `setMessage(Component)`
  - `isCancelled()` / `setCancelled(boolean)`

- `CustomBlockPlaceEvent`
  - `getBlock()`
  - `getReplacedBlockState()`
  - `getItemInHand()`
  - `getPlayer()`
  - `getMessage()`
  - `setMessage(Component)`
  - `canBuild()` / `setCanBuild(boolean)`
  - `isCancelled()` / `setCancelled(boolean)`

- `CustomPlayerChatEvent`
  - `getPlayer()`
  - `getMessage()`
  - `setMessage(Component)`
  - `getChannelName()`
  - `setChannelName(String)`
  - `isCancelled()` / `setCancelled(boolean)`

- `PlayerLevelUpEvent`
  - `getPlayer()`
  - `getNewLevel()`

- `CustomProcessCommandEvent`
  - `getPlayer()`
  - `getMessage()`
  - `getCommands()`
  - `addCommand(Command)`
  - `isCancelled()` / `setCancelled(boolean)`

## Requirements

- Java 21
- Spigot/Paper server compatible with the configured API version (`1.21`)
- Maven

## License

This project currently does not declare a license in the repository metadata.
