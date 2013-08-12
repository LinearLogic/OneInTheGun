package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class KillstreakChangeEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private final int oldStreak;
    private final int newStreak;

    public KillstreakChangeEvent(Player player, int from, int to) {
        super(player);
        oldStreak = from;
        newStreak = to;
    }

    public int getOldStreak() {
        return oldStreak;
    }

    public int getNewStreak() {
        return newStreak;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
