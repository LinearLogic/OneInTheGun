package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandKick extends OITGArenaCommand {

    public CommandKick(CommandSender sender, String[] args) {
        super(sender, args, 2, false, "kick <arena> <player/all>", "oneinthegun.arena.kick", false);
    }

    public void run() {
        if (args[1].equalsIgnoreCase("all")) {
            arena.broadcast(ChatColor.DARK_RED + "The arena has been emptied by an administrator.");
            arena.clearPlayers();
            sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Emptied arena " + arena.toString() + ".");
            return;
        }
        Player toKick = OITG.instance.getServer().getPlayer(args[1]);
        if (toKick == null) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Player " + args[1] + " is not online!");
            return;
        }
        if (!arena.containsPlayer(toKick)) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Player " + toKick.getName() + " is not in arena " + arena.toString() + ".");
            return;
        }
        arena.removePlayer(toKick, false);
        arena.broadcast(ChatColor.DARK_RED + toKick.getName() + " was kicked from the arena.");
        toKick.sendMessage(OITG.prefix + ChatColor.DARK_RED + "You were kicked from arena " + arena.toString() + ".");
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Kicked " + toKick.getName() + " from arena " + arena.toString() + ".");
    }
}
