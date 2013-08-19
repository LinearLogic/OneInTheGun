package com.entrocorp.linearlogic.oneinthegun.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class GeneralListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        Arena arena = OITG.instance.getArenaManager().getArena(event.getPlayer());
        if (arena != null && !arena.isBlockBreakingAllowed()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "Block breaking is disabled in this arena.");
            return;
        }
        arena = OITG.instance.getArenaManager().getArena(event.getBlock().getLocation());
        if (arena == null)
            return;
        if (!event.getPlayer().hasPermission("dodgeball.arena.signs")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "You don't have permission to break arena signs!");
            return;
        }
        arena.removeSignLocation(event.getBlock().getLocation());
        event.getPlayer().sendMessage(OITG.prefix + "Removed a sign for arena: " + arena.toString());
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        Player player = event.getPlayer();
        Arena arena = OITG.instance.getArenaManager().getArena(event.getClickedBlock().getLocation());
        if (arena == null)
            return;
        if (!player.hasPermission("oneinthegun.arena.join")) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "You don't have permission to play!");
            return;
        }
        if (arena.isIngame()) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "A game is already in progress in that arena, choose another.");
            return;
        }
        if (arena.isClosed()) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "That arena is closed, choose another.");
            return;
        }
        if (arena.getPlayerCount() >= arena.getPlayerLimit() && arena.getPlayerLimit() != -1) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "That arena is full, choose another.");
            return;
        }
        if (arena.getLobby() == null) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "That arena has not been set up: no lobby has been set.");
            return;
        }
        if (arena.getSpawns().length == 0) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "That arena has not been set up: no spawn points have been set.");
            return;
        }
        if (OITG.instance.getArenaManager().getArena(player) != null) {
            player.sendMessage(OITG.prefix + ChatColor.RED + "You are already in an arena!");
            return;
        }
        arena.addPlayer(player);
        player.teleport(arena.getLobby());
    }

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
        final Arena arena = OITG.instance.getArenaManager().getArena(event.getLine(1));
        if (arena == null) {
            event.getPlayer().sendMessage(OITG.prefix + ChatColor.RED + "The sign contains an invalid arena name!");
            event.setCancelled(true);
            return;
        }
        final Location signLoc = event.getBlock().getLocation();
        OITG.instance.getServer().getScheduler().scheduleSyncDelayedTask(OITG.instance, new Runnable() {
            public void run() {
                arena.addSignLocation(signLoc);
            }
        });
        event.getPlayer().sendMessage(OITG.prefix + "Created a sign for arena: " + arena.toString());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!OITG.joinToLobby)
            return;
        final String joinedName = event.getPlayer().getName();
        final Location globby = OITG.instance.getArenaManager().getGlobalLobby();
        if (globby == null)
            return;
        OITG.instance.getServer().getScheduler().scheduleSyncDelayedTask(OITG.instance, new Runnable() {
            public void run() {
                Player joined = OITG.instance.getServer().getPlayerExact(joinedName);
                if (joined != null)
                    joined.teleport(globby);
            }
        }, 5L);
    }
}
