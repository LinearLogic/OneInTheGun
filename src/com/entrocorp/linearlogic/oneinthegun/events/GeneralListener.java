package com.entrocorp.linearlogic.oneinthegun.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class GeneralListener {

    @EventHandler(ignoreCancelled = true)
    public void onSignChange(SignChangeEvent event) {
        if (OITG.instance.getArenaManager().getArena(event.getBlock().getLocation()) != null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "Arena signs cannot be edited!");
            return;
        }
        if (!event.getLine(0).equalsIgnoreCase("oitg"))
            return;
        if (!event.getPlayer().hasPermission("oneinthegun.arena.signs")) {
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "You don't have permission to create arena signs!");
            event.setCancelled(true);
            return;
        }
        Arena arena = OITG.instance.getArenaManager().getArena(event.getLine(1));
        if (arena == null) {
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "The sign contains an invalid arena name!");
            event.setCancelled(true);
            return;
        }
        event.setLine(0, arena.toString());
        event.setLine(1, null);
        event.setLine(2, arena.getState());
        event.setLine(3, arena.getPlayerCount() + "/" + arena.getPlayerLimit());
        arena.addSignLocation(event.getBlock().getLocation());
        event.getPlayer().sendMessage(OITG.prefix + ChatColor.GRAY + "Created a sign for arena: " + arena.toString());
    }
}
