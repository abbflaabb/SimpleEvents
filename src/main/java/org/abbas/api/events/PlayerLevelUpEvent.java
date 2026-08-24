package org.abbas.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event representing a player's level-up operation.
 *
 * <p>This event is cancellable. It has no direct Bukkit equivalent, so the
 * plugin implementing the leveling system is responsible for firing it and
 * checking {@link #isCancelled()} before applying the level change.</p>
 */
public class PlayerLevelUpEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final int oldLevel;
    private final int newLevel;

    private boolean cancelled;

    /**
     * Creates a level-up event.
     *
     * @param player the player whose level is changing
     * @param oldLevel the player's previous level
     * @param newLevel the player's new level
     */
    public PlayerLevelUpEvent(Player player, int oldLevel, int newLevel) {
        this.player = player;
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
        this.cancelled = false;
    }

    /** @return the player whose level is changing */
    public Player getPlayer() {
        return player;
    }

    /** @return the level before the level-up */
    public int getOldLevel() {
        return oldLevel;
    }

    /** @return the level after the level-up */
    public int getNewLevel() {
        return newLevel;
    }

    /** @return {@code true} when the level-up should be prevented */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /** @param cancelled whether the level-up should be prevented */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /** @return the handler list used by this event */
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /** @return the handler list used by Bukkit's event system */
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
