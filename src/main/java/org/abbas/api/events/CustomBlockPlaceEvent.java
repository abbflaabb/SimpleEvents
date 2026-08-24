package org.abbas.api.events;

import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

/**
 * Custom event fired when a player places a block through SimpleEvents.
 *
 * <p>The event exposes both Bukkit-style cancellation and the build state,
 * allowing dependent plugins to control whether the placement is permitted.</p>
 */
public class CustomBlockPlaceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Block block;
    private final BlockState replacedBlockState;
    private final ItemStack itemInHand;
    private final Player player;

    private Component message;
    private boolean cancelled;

    /**
     * Creates a custom block-place event.
     *
     * @param block the block being placed
     * @param replacedBlockState the state of the block being replaced
     * @param itemInHand the item used for placement
     * @param message the message associated with the event
     * @param player the player placing the block
     */
    public CustomBlockPlaceEvent(
            @NonNull Block block,
            @NonNull BlockState replacedBlockState,
            @NonNull ItemStack itemInHand,
            @NonNull Component message,
            @NonNull Player player
    ) {
        this.block = Objects.requireNonNull(block, "block");
        this.replacedBlockState = Objects.requireNonNull(replacedBlockState, "replacedBlockState");
        this.itemInHand = Objects.requireNonNull(itemInHand, "itemInHand");
        this.message = Objects.requireNonNull(message, "message");
        this.player = Objects.requireNonNull(player, "player");
    }

    /** @return the placed block */
    public @NonNull Block getBlock() {
        return block;
    }

    /** @return the state of the block that was replaced */
    public @NonNull BlockState getReplacedBlockState() {
        return replacedBlockState;
    }

    /** @return the item used to place the block */
    public @NonNull ItemStack getItemInHand() {
        return itemInHand;
    }

    /** @return the player placing the block */
    public @NonNull Player getPlayer() {
        return player;
    }

    /** @return the current event message */
    public @NonNull Component getMessage() {
        return message;
    }

    /** @param message the new event message */
    public void setMessage(@NonNull Component message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    /** @return {@code true} when building is currently allowed */
    public boolean canBuild() {
        return !cancelled;
    }

    /** @param canBuild whether building should be allowed */
    public void setCanBuild(boolean canBuild) {
        this.cancelled = !canBuild;
    }

    /** @return {@code true} when the placement is cancelled */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /** @param cancelled whether the placement should be cancelled */
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
