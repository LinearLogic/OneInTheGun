package com.entrocorp.linearlogic.oneinthegun.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandArenas extends OITGCommand {

    public CommandArenas(CommandSender sender, String[] args) {
        super(sender, args, 0, "arenas [state]", "oneinthegun.arena.list", false);
    }

    public void run() {
        String waiting = ChatColor.GREEN + "\nWaiting: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getWaitingArenas()),
                starting = ChatColor.YELLOW + "\nStarting: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getStartingArenas()),
                ingame = ChatColor.RED + "\nIn game: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getIngameArenas()),
                closed = ChatColor.DARK_RED + "\nClosed: " + ChatColor.GRAY + Arrays.toString(OITG.instance.getArenaManager().getClosedArenas());

        sender.sendMessage(ChatColor.GRAY + "[]" + ChatColor.RED + "===" + ChatColor.GRAY + "[" + ChatColor.DARK_RED +
                "Arenas" + ChatColor.GRAY + "]" + ChatColor.RED + "===" + ChatColor.GRAY + "[]" + (args.length == 0 ?
                waiting + starting + ingame + closed : args[0].equalsIgnoreCase("waiting") ? waiting :
                args[0].equalsIgnoreCase("starting") ? starting : args[0].equalsIgnoreCase("ingame") ? ingame :
                args[0].equalsIgnoreCase("closed") ? closed : waiting + starting + ingame + closed));
    }

}
