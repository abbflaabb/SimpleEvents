package org.abbas.api.events;

import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

/**
 * Custom event fired when a player breaks a block through SimpleEvents.
 *
 * <p>This event mirrors Bukkit's block-break cancellation state and allows
 * dependent plugins to modify the message associated with the event.</p>
 */
public class CustomBlockBreakEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Block block;

    private Component message;
    private boolean cancelled;

    /**
     * Creates a custom block-break event.
     *
     * @param player the player who broke the block
     * @param message the message associated with the event
     * @param block the block that was broken
     * @param cancelled the initial cancellation state
     */
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

    /** @return the player who broke the block */
    public @NonNull Player getPlayer() {
        return player;
    }

    /** @return the broken block */
    public @NonNull Block getBlock() {
        return block;
    }

    /** @return the current event message */
    public @NonNull Component getMessage() {
        return message;
    }

    /** @param message the new event message */
    public void setMessage(@NonNull Component message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    /** @return {@code true} when the block-break action is cancelled */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /** @param cancelled whether the block-break action should be cancelled */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /** @return the handler list used by this event */
    public static @NonNull HandlerList getHandlerList() {
        return HANDLERS;
    }

    /** @return the handler list used by Bukkit's event system */
    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
