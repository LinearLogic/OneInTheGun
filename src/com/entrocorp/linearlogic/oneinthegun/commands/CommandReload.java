package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandReload extends OITGCommand {

    public CommandReload(CommandSender sender, String[] args) {
        super(sender, args, "reload", "oneinthegun.reload", false);
    }

    public boolean run() {
        OITG.instance.reloadConfig();
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Reloaded the config.");
        return true;
    }
}
