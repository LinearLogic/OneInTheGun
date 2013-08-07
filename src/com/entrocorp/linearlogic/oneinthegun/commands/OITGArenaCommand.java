package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public abstract class OITGArenaCommand extends OITGCommand {

    protected Arena arena;

    public OITGArenaCommand(CommandSender sender, String[] args, String usage, String permission, boolean mustBePlayer) {
        super(sender, args, usage, permission, mustBePlayer);
    }

    public boolean validate() {
        if (args.length == 0) { // There must be enough arguments for an arena to have been specified
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Too few arguments.");
            return false;
        }
        arena = OITG.instance.getArenaManager().getArena(args[0]);
        if (arena == null) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "There is no arena by that name.");
            return false;
        }
        return true;
    }
}
