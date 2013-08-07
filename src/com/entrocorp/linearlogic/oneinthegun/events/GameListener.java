package com.entrocorp.linearlogic.oneinthegun.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class GameListener implements Listener {

    private boolean listening = false;

    public boolean isRegistered() {
        return listening;
    }

    public void setRegistered(boolean registered) {
        if (listening == registered)
            return;
        if (registered)
            OITG.instance.getServer().getPluginManager().registerEvents(this, OITG.instance);
        else
            HandlerList.unregisterAll(this);
        listening = registered;
    }
}

