package com.entrocorp.linearlogic.oneinthegun.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandArenas extends OITGCommand {

    public CommandArenas(CommandSender sender, String[] args) {
        super(sender, args, 0, "arenas", "oneinthegun.arena.list", false);
    }

    public void run() {
        sender.sendMessage(ChatColor.GRAY + "[]" + ChatColor.RED + "===" + ChatColor.GRAY + "[" + ChatColor.DARK_RED +
                "Arenas" + ChatColor.GRAY + "]" + ChatColor.RED + "===" + ChatColor.GRAY + "[]\n" + ChatColor.GREEN +
                "Waiting: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getWaitingArenas()) +
                ChatColor.RED + "\nIn game: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getIngameArenas()) +
                ChatColor.DARK_RED + "\nClosed: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getClosedArenas()));
    }

}
