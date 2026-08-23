package org.abbas.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CustomPlayerJoinEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private @Nullable String message;

    public CustomPlayerJoinEvent(Player player, @Nullable String message) {
        this.player = player;
        this.message = message;
    }
    public @NonNull Player getPlayer() {
        return player;
    }
    public @Nullable String getMessage() {
        return message;
    }
    public void setMessage(@Nullable String message) {
        this.message = message;
    }
    public static @NonNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
