package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class CommandSearch extends OITGCommand {

    public CommandSearch(CommandSender sender, String[] args) {
        super(sender, args, 1, "search <username>", "oneinthegun.search", false);
    }

    public void run() {
        Player sought = OITG.instance.getServer().getPlayer(args[0]);
        if (sought == null) {
            sender.sendMessage(OITG.prefix + "Player " + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " is not online!");
            return;
        }
        Arena arena = OITG.instance.getArenaManager().getArena(sought);
        sender.sendMessage(OITG.prefix + ChatColor.YELLOW + sought.getName() + ChatColor.GRAY + (arena == null ?
                " isn't in an arena." : " is in arena " + ChatColor.YELLOW + arena.toString() + ChatColor.GRAY + "."));
    }

}
