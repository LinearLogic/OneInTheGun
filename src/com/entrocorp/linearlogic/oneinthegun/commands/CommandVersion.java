package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandVersion extends OITGCommand {

    public CommandVersion(CommandSender sender, String[] args) {
        super(sender, args, "version", null, false);
    }

    public void run() {
        sender.sendMessage(OITG.prefix + "Running version " + ChatColor.LIGHT_PURPLE + OITG.instance.getDescription().getVersion() +
                ChatColor.GRAY + " by LinearLogic.");
    }
}
