package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public abstract class OITGCommand {

    protected final CommandSender sender;
    protected String[] args;
    protected final String usage;

    protected final String permission;
    protected final boolean mustBePlayer;

    public OITGCommand(CommandSender sender, String[] args, String usage, String permission, boolean mustBePlayer) {
        this.sender = sender;
        this.args = args;
        this.usage = usage;
        this.permission = permission;
        this.mustBePlayer = mustBePlayer;
    }

    public abstract void run();

    public boolean authorizeSender() {
        if (mustBePlayer && !(sender instanceof Player)) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Only players can run that command.");
            return false;
        }
        if (sender instanceof Player && permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Access denied!");
            return false;
        }
        return true;
    }

    public void msgUsage() {
        sender.sendMessage(OITG.prefix + ChatColor.YELLOW + "Usage: " + ChatColor.GRAY + "/oitg " + usage);
    }
}
