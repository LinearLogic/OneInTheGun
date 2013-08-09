package com.entrocorp.linearlogic.oneinthegun.commands;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.util.HLComparablePair;
import com.entrocorp.linearlogic.oneinthegun.util.Pair;

public class CommandLeaderboard extends OITGArenaCommand {

    public CommandLeaderboard(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "leaderboard <arena> [page number]", "oneinthegun.arena.leaderboard", false);
    }

    public void run() {
        if (!arena.isIngame()) {
            sender.sendMessage(OITG.prefix + "The round has not yet started in that arena.");
            return;
        }

        int pageNum = -1, pageMax = (int) Math.ceil(arena.getPlayerCount() / 10.0);
        if (args.length > 1) {
            try {
                pageNum = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) { }
            if (pageNum < 1 || pageNum > pageMax) {
                sender.sendMessage(OITG.prefix + "The page number must be a positive value no greater than " + pageMax + ".");
                return;
            }
        } else {
            pageNum = 1;
        }

        StringBuffer printout = new StringBuffer(ChatColor.DARK_GRAY + "[]" + ChatColor.RED + "===" + ChatColor.DARK_GRAY +
                "[" + ChatColor.DARK_RED + "Leaderboard: " + arena.toString() + ChatColor.DARK_GRAY + ChatColor.BOLD +
                " | " + ChatColor.GRAY + "Page " + pageNum + " of " + pageMax + ChatColor.DARK_GRAY + "]" + ChatColor.RED +
                "===" + ChatColor.DARK_GRAY + "[]");
        Set<Pair<Player, HLComparablePair<Integer, Integer>>> scores = arena.getSortedPlayerScores();
        Iterator<Pair<Player, HLComparablePair<Integer, Integer>>> iter = scores.iterator();
        int index = 0, start = (pageNum - 1) * 10, end = start + 10;
        while (++index <= scores.size()) {
            if (index > start && index <= end) {
                Pair<Player, HLComparablePair<Integer, Integer>> entry = iter.next();
                printout.append("\n  " + (index == 1 ? "" + ChatColor.GOLD + ChatColor.BOLD + ChatColor.ITALIC + "1. " :
                        ChatColor.DARK_GRAY + Integer.toString(index) + ". ") + ChatColor.GRAY + entry.getX().getName() +
                        ChatColor.DARK_GRAY + ChatColor.BOLD + " | " + ChatColor.GREEN + entry.getY().getX() + ChatColor.DARK_GRAY +
                        ChatColor.BOLD + " | " + ChatColor.RED + entry.getY().getY());
                continue;
            }
            iter.next();
        }
        sender.sendMessage(printout.toString());
    }
}
