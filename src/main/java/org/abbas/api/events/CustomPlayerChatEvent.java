package org.abbas.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import net.kyori.adventure.text.Component;

/**
 * Custom event fired for player chat messages.
 *
 * <p>The event supports cancellation, message modification, and a logical
 * channel name. The current SimpleEvents bridge uses the {@code global}
 * channel by default.</p>
 */
public class CustomPlayerChatEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private boolean cancelled = false;
    private Component message;
    private String channelName;

    /**
     * Creates a custom player-chat event.
     *
     * @param player the player who sent the message
     * @param message the chat message
     * @param channelName the logical chat channel
     * @param cancelled the initial cancellation state
     */
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

    /** @return the player who sent the message */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /** @return the current chat message */
    @NotNull
    public Component getMessage() {
        return this.message;
    }

    /** @param message the new chat message */
    public void setMessage(@NotNull Component message) {
        this.message = message;
    }

    /** @return the logical chat channel */
    @NotNull
    public String getChannelName() {
        return this.channelName;
    }

    /** @param channelName the new logical chat channel */
    public void setChannelName(@NotNull String channelName) {
        this.channelName = channelName;
    }

    /** @return {@code true} when the chat message is cancelled */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /** @param cancel whether the chat message should be cancelled */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /** @return the handler list used by this event */
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /** @return the handler list used by Bukkit's event system */
    @Override
    public @NonNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
