package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandRename extends OITGArenaCommand {

    public CommandRename(CommandSender sender, String[] args) {
        super(sender, args, 2, true, "rename <arena> <name>", "oneinthegun.arena.rename", false);
    }

    public void run() {
        if (OITG.instance.getArenaManager().getArena(args[1]) != null) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "There is already an arena with that name.");
            return;
        }
        String oldName = arena.toString();
        sender.sendMessage(OITG.prefix + (arena.setName(args[1]) ? "Renamed arena " + ChatColor.YELLOW + oldName +
                ChatColor.GRAY + " to " + ChatColor.YELLOW + arena.toString() : ChatColor.RED + "There is already an" +
                " arena by that name."));
    }

}
