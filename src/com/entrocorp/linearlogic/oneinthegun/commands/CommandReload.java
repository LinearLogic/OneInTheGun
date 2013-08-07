package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandReload extends OITGCommand {

    public CommandReload(CommandSender sender, String[] args) {
        super(sender, args, 0, "reload", "oneinthegun.reload", false);
    }

    public void run() {
        OITG.instance.reloadConfig();
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Reloaded the config.");
    }
}
