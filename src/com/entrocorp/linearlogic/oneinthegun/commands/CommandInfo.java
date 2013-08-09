package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandInfo extends OITGArenaCommand {

    public CommandInfo(CommandSender sender, String[] args) {
        super(sender, args, 1, false, "info <arena>", "oneinthegun.arena.info", false);
    }

    public void run() {
        sender.sendMessage(ChatColor.GRAY + "[]" + ChatColor.RED + "===" + ChatColor.GRAY + "[" + ChatColor.DARK_RED +
                "Arena Info: " + arena.toString() + ChatColor.GRAY + "]" + ChatColor.RED + "===" + ChatColor.GRAY + "[]\n" +
                "Status: " + arena.getState() + ChatColor.GRAY + "\nPlayers: " + ChatColor.LIGHT_PURPLE + arena.getPlayerCount() +
                ChatColor.GRAY + "/" + ChatColor.LIGHT_PURPLE + arena.getPlayerLimit() + ChatColor.GRAY + "\nTime limit: " +
                ChatColor.LIGHT_PURPLE + arena.getTimeLimitFormatted() + ChatColor.GRAY + "\nKill limit: " + (arena.getKillLimit() == -1 ?
                "none" : ChatColor.LIGHT_PURPLE + Integer.toString(arena.getKillLimit())) + ChatColor.GRAY + "\n\nBlock placing: " +
                parseEnabled(arena.isBlockPlacingAllowed()) + "\nBlock breaking: " + parseEnabled(arena.isBlockBreakingAllowed()) +
                "\nHealth regeneration: " + parseEnabled(arena.isHealthRegenAllowed()) + "\nHunger: " + parseEnabled(arena.isHungerAllowed()) +
                "\nItem dropping: " + parseEnabled(arena.isItemDroppingAllowed()) + "\nItem pickup: " + parseEnabled(arena.isItemPickupAllowed()) +
                "\nMob combat: " + parseEnabled(arena.isMobCombatAllowed()));
    }

    private String parseEnabled(boolean enabled) {
        return (enabled ? ChatColor.GREEN + "enabled" : ChatColor.DARK_RED + "disabled") + ChatColor.GRAY;
    }

}
