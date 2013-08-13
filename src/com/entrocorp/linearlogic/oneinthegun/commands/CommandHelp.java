package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandHelp extends OITGCommand {

    private static String[] commands = {"addspawn <arena>", "arenas [state]", "clearspawns <arena>", "close <arena>",
        "create <name>", "delete <arena>", "fstart <arena> [delay]", "fstop <arena> [delay]", "help [page number]",
        "info <arena>", "join <arena>", "kick <arena> <player/all>", "leaderboard <arena> [page number]", "leave",
        "open <arena>", "reload", "rename <arena> <name>", "save <arena>", "search <username>", "setgloballobby",
        "setlobby <arena>", "settings <arena> <setting> <value>", "version"};

    private static String[] descriptions = {"place an arena spawn point at your location", "list arenas by state",
        "remove all spawn points from an arena", "close an open arena", "create a new arena", "delete an arena",
        "force the round in an arena to start (delay is in seconds)", "force the round in an arena to end (delay is in seconds)",
        "display command information", "display arena settings and information", "join an arena", "kick a player from an " +
        "arena or empty an entire arena", "display the kill-death leaderboard for an arena", "leave an arena", "open a closed arena",
        "reload the plugin configuration", "rename an arena", "save an edited arena", "lookup the arena in which a user is playing",
        "place the global lobby at your location", "place the lobby of an arena", "modify an arena setting", "display the plugin version"};

    public CommandHelp(CommandSender sender, String[] args) {
        super(sender, args, 0, "help [page number]", null, false);
    }

    public void run() {
        int pageNum = -1;
        if (args.length > 0) {
            try {
                pageNum = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) { }
            if (pageNum < 1 || pageNum > 3) {
                sender.sendMessage(OITG.prefix + "The page number must be between 1 and 3.");
                return;
            }
        } else {
            pageNum = 1;
        }

        StringBuffer printout = new StringBuffer(ChatColor.DARK_GRAY + "[]" + ChatColor.RED + "===" + ChatColor.DARK_GRAY +
                "[" + ChatColor.DARK_RED + "OITG Commands" + ChatColor.DARK_GRAY + ChatColor.BOLD + " | " + ChatColor.GRAY +
                "Page " + pageNum + " of 3" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + "===" + ChatColor.DARK_GRAY + "[]");
        for (int i = (pageNum - 1) * 8; i < pageNum * 8; i++) {
            if (i >= commands.length)
                break;
            printout.append(ChatColor.AQUA + "\n/oitg " + commands[i] + ChatColor.GRAY + " - " + descriptions[i]);
        }
        sender.sendMessage(printout.toString());
    }
}
