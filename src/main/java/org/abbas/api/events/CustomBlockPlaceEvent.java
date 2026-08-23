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

public class CustomBlockPlaceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Block block;
    private final BlockState replacedBlockState;
    private final ItemStack itemInHand;
    private final Player player;

    private Component message;
    private boolean cancelled;

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

    public @NonNull Block getBlock() {
        return block;
    }

    public @NonNull BlockState getReplacedBlockState() {
        return replacedBlockState;
    }

    public @NonNull ItemStack getItemInHand() {
        return itemInHand;
    }

    public @NonNull Player getPlayer() {
        return player;
    }

    public @NonNull Component getMessage() {
        return message;
    }

    public void setMessage(@NonNull Component message) {
        this.message = Objects.requireNonNull(message, "message");
    }

    public boolean canBuild() {
        return !cancelled;
    }

    public void setCanBuild(boolean canBuild) {
        this.cancelled = !canBuild;
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