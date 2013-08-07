package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandOpen extends OITGArenaCommand {

    public CommandOpen(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "open <arena>", "oneinthegun.arena.toggleclosed", false);
    }

    public void run() {
        if (!arena.isClosed()) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "That arena is already open.");
            return;
        }
        arena.setClosed(false);
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Opened arena " + arena.toString() + ".");
    }

}
