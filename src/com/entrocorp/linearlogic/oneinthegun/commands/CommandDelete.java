package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandDelete extends OITGArenaCommand {

    public CommandDelete(CommandSender sender, String[] args) {
        super(sender, args, 1, true, "delete <arena>", "oneinthegun.arena.delete", false);
    }

    public void run() {
        OITG.instance.getArenaManager().deleteArena(arena);
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Deleted arena " + arena.toString() + ".");
    }

}
