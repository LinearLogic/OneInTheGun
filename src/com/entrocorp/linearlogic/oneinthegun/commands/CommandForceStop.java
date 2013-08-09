package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandForceStop extends OITGArenaCommand {

    public CommandForceStop(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "fstop <arena> [delay]", "oneinthegun.arena.forcestop", false);
    }

    public void run() {
        if (!arena.isIngame()) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "No round is in progress in that arena.");
            return;
        }
        if (args.length == 1) {
            arena.setTimeRemaining(0);
            sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Forced the round in arena " + arena.toString() + " to end immediately.");
            return;
        }
        int delay = -1;
        try {
            delay = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) { }
        if (delay < 0) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "The delay must be a number no less than zero.");
            return;
        }
        arena.setTimeRemaining(delay);
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Forced the round in arena " + arena.toString() + " to end in " +
                delay + " seconds.");
    }

}
