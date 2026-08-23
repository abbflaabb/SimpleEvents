package org.abbas.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import net.kyori.adventure.text.Component;

public class CustomPlayerChatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private boolean cancelled = false;
    private Component message;
    private String channelName;
    public CustomPlayerChatEvent(
            @NonNull Player player,
            @NonNull Component message,
            @NonNull String channelName,
            boolean cancelled
    ) {
        super(true);
        this.player = player;
        this.message = message;
        this.channelName = channelName;
        this.cancelled = cancelled;
    }
    @NotNull
    public Player getPlayer() {
        return this.player;
    }
    @NotNull
    public Component getMessage() {
        return this.message;
    }
    public void setMessage(@NotNull Component message) {
        this.message = message;
    }
    @NotNull
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(@NotNull String channelName) {
        this.channelName = channelName;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
