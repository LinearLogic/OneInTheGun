package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class VictoryEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public VictoryEvent(final Player player) {
        super(player);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
