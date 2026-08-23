# simpleEvents

A lightweight Spigot/Paper plugin that exposes a small API for firing custom Bukkit-style events from Java code.

[![](https://jitpack.io/v/abbflaabb/SimpleEvents.svg)](https://jitpack.io/#abbflaabb/SimpleEvents)


## Overview

`simpleEvents` is designed for Minecraft server plugins that need to trigger custom gameplay events without tightly coupling code to the Bukkit event system. It provides a plugin bootstrap and a set of custom event classes for common scenarios such as:

- player join messages
- player quit messages
- level-up events
- command processing events

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

1. Build the plugin with Maven.
2. Copy the generated JAR into your server's `plugins/` folder.
3. Start the server.

## API usage

### Register a listener

```java
SimpleEvents.registerListener(new Listener() {
    @EventHandler
    public void onJoin(CustomPlayerJoinEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Bukkit.getLogger().info(player.getName() + " joined: " + message);
    }
});
```

### Trigger custom events

```java
SimpleEvents.callCustomPlayerJoin(player, "Welcome back!");
SimpleEvents.callCustomPlayerQuit(player, "See you soon!");
SimpleEvents.callPlayerLevelUp(player, 10);
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
