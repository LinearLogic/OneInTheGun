package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandSetLobby extends OITGArenaCommand {

    public CommandSetLobby(CommandSender sender, String[] args) {
        super(sender, args, 1, true, "setlobby <arena>", "oneinthegun.arena.setlobby", true);
    }

    public void run() {
        arena.setLobby(((Player) sender).getLocation());
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Placed the lobby for arena " + arena.toString() + ".");
    }

}
