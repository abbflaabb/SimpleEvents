package org.abbas.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PlayerLevelUpEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final int newLevel;

    public PlayerLevelUpEvent(Player player, int newLevel) {
        this.player = player;
        this.newLevel = newLevel;
    }
    public Player getPlayer() {
        return player;
    }
    public int getNewLevel() {
        return newLevel;
    }
    public static @NonNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * @return HANDLER_LIST
     */
    @Override
    public @Nullable HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
