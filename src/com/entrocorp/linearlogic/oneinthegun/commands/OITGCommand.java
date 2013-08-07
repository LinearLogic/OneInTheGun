package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public abstract class OITGCommand {

    protected CommandSender sender;
    protected String[] args;
    protected String usage;

    public OITGCommand(CommandSender sender, String[] args, String usage) {
        this.sender = sender;
        this.args = args;
        this.usage = usage;
    }

    public abstract boolean run();

    public void msgUsage() {
        sender.sendMessage(OITG.prefix + ChatColor.YELLOW + "Usage: " + ChatColor.GRAY + "/oitg " + usage);
    }
}
