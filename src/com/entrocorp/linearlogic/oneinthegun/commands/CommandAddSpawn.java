package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandAddSpawn extends OITGArenaCommand{

    public CommandAddSpawn(CommandSender sender, String[] args) {
        super(sender, args, 1, true, "addspawn <arena>", "oneinthegun.arena.spawns.add", true);
    }

    public void run() {
        arena.addSpawn(((Player) sender).getLocation());
        sender.sendMessage(OITG.prefix + ChatColor.GREEN + "Added a spawn to arena " + arena.toString() + ".");
    }
}
