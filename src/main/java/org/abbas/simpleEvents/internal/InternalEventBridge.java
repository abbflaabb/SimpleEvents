package org.abbas.simpleEvents.internal;

import org.abbas.simpleEvents.SimpleEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Internal bridge that listens to vanilla Bukkit events and automatically
 * fires the corresponding simpleEvents custom events.
 *
 * This exists purely so that plugins depending on simpleEvents can listen
 * to {@link org.abbas.api.events.CustomPlayerJoinEvent} /
 * {@link org.abbas.api.events.CustomPlayerQuitEvent} directly, without
 * needing to call {@link SimpleEvents#callCustomPlayerJoin} /
 * {@link SimpleEvents#callCustomPlayerQuit} themselves.
 *
 * Intentionally NOT part of the public API surface (package-private
 * visibility would be ideal, but Bukkit requires the listener class to be
 * accessible for registration) — dependent plugins should keep using the
 * static {@code SimpleEvents.callCustom...} methods if they want to fire
 * events manually; this class only covers the automatic join/quit hook.
 *
 * Registered at {@link EventPriority#MONITOR} so it observes the final
 * state of the event after every other plugin has had a chance to act,
 * without itself mutating vanilla event data (e.g. join/quit messages) —
 * that responsibility is left to whichever plugin consumes the custom
 * event, matching simpleEvents' existing "manual API, dependent plugin
 * decides" design philosophy.
 */
public final class InternalEventBridge implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        SimpleEvents.callCustomPlayerJoin(event.getPlayer(), null);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        SimpleEvents.callCustomPlayerQuit(event.getPlayer(), null);
    }
}
