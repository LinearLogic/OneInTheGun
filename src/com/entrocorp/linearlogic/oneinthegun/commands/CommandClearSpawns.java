package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandClearSpawns extends OITGArenaCommand {

    public CommandClearSpawns(CommandSender sender, String[] args) {
        super(sender, args, 1, true, "clearspawns <arena>", "oneinthegun.arena.spawns.clear", false);
    }

    public void run() {
        if (arena.getSpawns().length == 0) {
            sender.sendMessage(OITG.prefix + "No spawns have been set for that arena.");
            return;
        }
        arena.clearSpawns();
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Cleared all spawns in arena " + arena.toString() + ".");
    }
}
