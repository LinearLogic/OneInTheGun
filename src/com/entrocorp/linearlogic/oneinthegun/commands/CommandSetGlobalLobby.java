package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandSetGlobalLobby extends OITGCommand {

    public CommandSetGlobalLobby(CommandSender sender, String[] args) {
        super(sender, args, 0, "setgloballobby", "oneinthegun.setgloballobby", true);
    }

    public void run() {
        OITG.instance.getArenaManager().setGlobalLobby(((Player) sender).getLocation(), true);
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Updated the global lobby.");
    }

}
