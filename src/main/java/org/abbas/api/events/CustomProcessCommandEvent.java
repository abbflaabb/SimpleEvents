package org.abbas.api.events;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomProcessCommandEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private String message;
    private boolean cancel = false;
    private final List<Command> commands;

    public CustomProcessCommandEvent(@NotNull Player player, @NotNull String message, @NotNull List<Command> commands) {
        this.player = player;
        this.message = message;
        // FIXED: Create a copy of the passed list so it isn't empty or directly linked
        this.commands = new ArrayList<>(commands);
    }
    @NotNull
    public Player getPlayer() {
        return player;
    }
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @NotNull
    public List<Command> getCommands() {
        return commands;
    }

    public void addCommand(@NotNull Command command) {
        this.commands.add(command);
    }

    public boolean isCancelled() {
        return cancel;
    }
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
