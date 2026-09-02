package org.abbas.api.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class CustomPlayerMoveEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Location from;
    private Location to;
    private boolean cancelled;

    public CustomPlayerMoveEvent(@NotNull Player who, @NotNull Location from, @NotNull Location to) {
        super(who);
        this.from = from;
        this.to = to;
    }
    public CustomPlayerMoveEvent(@NotNull Player who, @NotNull Location from, @NotNull Location to, boolean async) {
        super(who, async);
        this.from = from;
        this.to = to;
    }
    /**
     * Gets the location the player moved from.
     */
    public @NotNull Location getFrom() {
        return from;
    }

    /**
     * Gets the location the player is moving to.
     */
    public @NotNull Location getTo() {
        return to;
    }

    /**
     * Changes the destination location.
     */
    public void setTo(@NotNull Location to) {
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}