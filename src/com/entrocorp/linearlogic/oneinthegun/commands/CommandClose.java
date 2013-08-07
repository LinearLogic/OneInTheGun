package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandClose extends OITGArenaCommand {

    public CommandClose(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "close <arena>", "oneinthegun.arena.toggleclosed", false);
    }

    public void run() {
        if (arena.isClosed()) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "That arena is already closed.");
            return;
        }
        arena.setClosed(true);
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Closed arena " + arena.toString() + ".");
    }

}
