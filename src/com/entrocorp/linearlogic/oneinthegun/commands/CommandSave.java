package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandSave extends OITGArenaCommand {

    public CommandSave(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "save <arena>", "oneinthegun.arena.save", false);
    }

    public void run() {
        sender.sendMessage(arena.save(false) ? ChatColor.GREEN + "Saved arena " + arena.toString() + "." : ChatColor.RED +
                "An critical error occurred while attempting to save arena " + arena.toString() + ".");
    }

}
