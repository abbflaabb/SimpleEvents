package org.abbas.api.events;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CustomProcessCommandEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private String message;
    private boolean cancel = false;
    private final List<Command> commands;

    public CustomProcessCommandEvent(@NonNull Player player, @NonNull String message, @NonNull List<Command> commands) {
        this.player = player;
        this.message = message;
        // FIXED: Create a copy of the passed list so it isn't empty or directly linked
        this.commands = new ArrayList<>(commands);
    }
    @NonNull
    public Player getPlayer() {
        return player;
    }
    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @NonNull
    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(@NonNull Command command) {
        this.commands.add(command);
    }

    public boolean isCancelled() {
        return cancel;
    }
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NonNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @NonNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
