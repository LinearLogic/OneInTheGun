package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandLoadListeners extends OITGCommand {

    public CommandLoadListeners(CommandSender sender, String[] args) {
        super(sender, args, 0, "loadlisteners", "oneinthegun.loadlisteners", false);
    }

    public void run() {
        sender.sendMessage(OITG.instance.getListenerManager().loadCustomListeners() ? ChatColor.GREEN + "Loaded custom listeners." :
            ChatColor.RED + "Errors occurred while loading custom listeners. Not all listeners may be functional.");
    }
}
