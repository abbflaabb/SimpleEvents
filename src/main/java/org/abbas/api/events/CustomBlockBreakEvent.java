package org.abbas.api.events;

import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class CustomBlockBreakEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Block block;

    private Component message;
    private boolean cancelled;

    public CustomBlockBreakEvent(
            @NonNull Player player,
            @NonNull Component message,
            @NonNull Block block,
            boolean cancelled
    ) {
        this.player = Objects.requireNonNull(player, "player");
        this.message = Objects.requireNonNull(message, "message");
        this.block = Objects.requireNonNull(block, "block");
        this.cancelled = cancelled;
    }

    public @NonNull Player getPlayer() {
        return player;
    }

    public @NonNull Block getBlock() {
        return block;
    }

    public @NonNull Component getMessage() {
        return message;
    }

    public void setMessage(@NonNull Component message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static @NonNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLERS;
    }
}