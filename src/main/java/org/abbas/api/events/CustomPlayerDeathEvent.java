package org.abbas.api.events;

import net.kyori.adventure.text.Component;
import org.bukkit.damage.DeathMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class CustomPlayerDeathEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private Component deathMessage;
    private final DeathMessageType deathMessageType;
    private final @Nullable Player killer;

    public CustomPlayerDeathEvent(
            @NonNull Player player,
            @NonNull Component deathMessage,
            @NonNull DeathMessageType deathMessageType,
            @Nullable Player killer
    ) {
        this.player = Objects.requireNonNull(player, "player");
        this.deathMessage = Objects.requireNonNull(deathMessage, "deathMessage");
        this.deathMessageType = Objects.requireNonNull(deathMessageType, "deathMessageType");
        this.killer = killer;
    }

    public @NonNull Player getPlayer() {
        return player;
    }

    public @NonNull Component getDeathMessage() {
        return deathMessage;
    }

    public void setDeathMessage(@NonNull Component deathMessage) {
        this.deathMessage = Objects.requireNonNull(deathMessage, "deathMessage");
    }

    public @NonNull DeathMessageType getDeathMessageType() {
        return deathMessageType;
    }

    public @Nullable Player getKiller() {
        return killer;
    }

    public static @NonNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLERS;
    }
}