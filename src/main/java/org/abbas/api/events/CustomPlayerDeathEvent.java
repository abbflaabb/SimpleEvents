package org.abbas.api.events;

import net.kyori.adventure.text.Component;
import org.bukkit.damage.DeathMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * Custom event fired when a player dies.
 *
 * <p>The event is intended for customizing the death message and exposing
 * information about the death source. It is not cancellable.</p>
 */
public class CustomPlayerDeathEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private Component deathMessage;
    private final DeathMessageType deathMessageType;
    private final @Nullable Player killer;

    /**
     * Creates a custom player-death event.
     *
     * @param player the player who died
     * @param deathMessage the current death message
     * @param deathMessageType the type of death message
     * @param killer the player responsible for the death, if any
     */
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

    /** @return the player who died */
    public @NonNull Player getPlayer() {
        return player;
    }

    /** @return the current death message */
    public @NonNull Component getDeathMessage() {
        return deathMessage;
    }

    /** @param deathMessage the new death message */
    public void setDeathMessage(@NonNull Component deathMessage) {
        this.deathMessage = Objects.requireNonNull(deathMessage, "deathMessage");
    }

    /** @return the death message type */
    public @NonNull DeathMessageType getDeathMessageType() {
        return deathMessageType;
    }

    /** @return the killer, or {@code null} when there is no player killer */
    public @Nullable Player getKiller() {
        return killer;
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
