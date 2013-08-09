package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandSettings extends OITGArenaCommand {

    public CommandSettings(CommandSender sender, String[] args) {
        super(sender, args, 3, false, "settings <arena> <setting> <value>", null, false);
    }

    public void run() {
        if (args.length > 3) {
            msgUsage();
            return;
        }
        String setting = args[1].toLowerCase();
        if (setting.contains("limit") && arena.getPlayerCount() != 0) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "The arena must be empty.");
            return;
        }

        if (setting.equals("player-limit")) {
            if (!checkPermission("oneinthegun.arena.settings.playerlimit"))
                return;
            int limit = 0;
            try {
                limit = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) { }
            if (limit < 2 && limit != -1) {
                sender.sendMessage(OITG.prefix + "The player limit must be a number greater than 1 (or equal to -1 to for no limit).");
                return;
            }
            arena.setPlayerLimit(limit);
            sender.sendMessage(OITG.prefix + "Updated the player limit for arena " + arena.toString());
            return;
        }

        if (setting.equals("time-limit")) {
            if (!checkPermission("oneinthegun.arena.settings.timelimit"))
                return;
            int limit = 0;
            try {
                limit = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) { }
            if (limit < 10 && limit != -1) {
                sender.sendMessage(OITG.prefix + "The time limit (in seconds) must be at least 10 (or equal to -1 to for no limit).");
                return;
            }
            if (limit == -1 && arena.getKillLimit() == -1) {
                sender.sendMessage(OITG.prefix + "Arena " + arena.toString() + " has no kill limit so it must have a time limit.");
                return;
            }
            arena.setTimeLimit(limit);
            sender.sendMessage(OITG.prefix + "Updated the time limit for arena " + arena.toString());
            return;
        }

        if (setting.equals("kill-limit")) {
            if (!checkPermission("oneinthegun.arena.settings.killlimit"))
                return;
            int limit = 0;
            try {
                limit = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) { }
            if (limit < 1 && limit != -1) {
                sender.sendMessage(OITG.prefix + "The kill limit must be a number greater than 0 (or equal to -1 to for no limit).");
                return;
            }
            if (limit == -1 && arena.getTimeLimit() == -1) {
                sender.sendMessage(OITG.prefix + "Arena " + arena.toString() + " has no time limit so it must have a kill limit.");
                return;
            }
            arena.setKillLimit(limit);
            sender.sendMessage(OITG.prefix + "Updated the kill limit for arena " + arena.toString());
            return;
        }

        boolean flag = false;
        if (args[2].equalsIgnoreCase("on")) {
            flag = true;
        } else {
            if (!args[2].equalsIgnoreCase("off")) {
                sender.sendMessage(OITG.prefix + "The flag value must be 'on' or 'off'.");
                return;
            }
        }

        if (setting.equals("block-place")) {
            if (!checkPermission("oneinthegun.arena.settings.blockplace"))
                return;
            if (arena.isBlockPlacingAllowed() == flag) {
                sender.sendMessage(OITG.prefix + "Block placing is already " + (flag ? "enabled." : "disabled."));
                return;
            }
            arena.allowBlockPlacing(flag);
            sender.sendMessage(OITG.prefix + "Block placing in arena " + arena.toString() + " is now " + (flag ? "enabled." : "disabled."));
            return;
        }

        if (setting.equals("block-break")) {
            if (!checkPermission("oneinthegun.arena.settings.blockbreak"))
                return;
            if (arena.isBlockBreakingAllowed() == flag) {
                sender.sendMessage(OITG.prefix + "Block breaking is already " + (flag ? "enabled." : "disabled."));
                return;
            }
            arena.allowBlockBreaking(flag);
            sender.sendMessage(OITG.prefix + "Block breaking in arena " + arena.toString() + " is now " + (flag ? "enabled." : "disabled."));
            return;
        }

        if (setting.equals("health-regen") || setting.equals("healing")) {
            if (!checkPermission("oneinthegun.arena.settings.healthregen"))
                return;
            if (arena.isHealthRegenAllowed() == flag) {
                sender.sendMessage(OITG.prefix + "Health regeneration is already " + (flag ? "enabled." : "disabled."));
                return;
            }
            arena.allowHealthRegen(flag);
            sender.sendMessage(OITG.prefix + "Health regeneration in arena " + arena.toString() + " is now " + (flag ? "enabled." : "disabled."));
            return;
        }

        if (setting.equals("hunger")) {
            if (!checkPermission("oneinthegun.arena.settings.hunger"))
                return;
            if (arena.isHungerAllowed() == flag) {
                sender.sendMessage(OITG.prefix + "Hunger is already " + (flag ? "enabled." : "disabled."));
                return;
            }
            arena.allowHunger(flag);
            sender.sendMessage(OITG.prefix + "Hunger in arena " + arena.toString() + " is now " + (flag ? "enabled." : "disabled."));
            return;
        }

        if (setting.equals("mob-combat")) {
            if (!checkPermission("oneinthegun.arena.settings.mobcombat"))
                return;
            if (arena.isMobInteractAllowed() == flag) {
                sender.sendMessage(OITG.prefix + "Mob combat is already " + (flag ? "enabled." : "disabled."));
                return;
            }
            arena.allowMobInteract(flag);
            sender.sendMessage(OITG.prefix + "Combat with mobs in arena " + arena.toString() + " is now " + (flag ? "enabled." : "disabled."));
            return;
        }

        sender.sendMessage(OITG.prefix + ChatColor.RED + "Invalid setting name. Supported settings: " + ChatColor.GRAY +
                "player-limit, time-limit, kill-limit, block-place, block-break, health-regen, hunger, mob-combat.");
    }

    private boolean checkPermission(String permission) {
        if (!(sender instanceof Player))
            return true;
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "You don't have permission to change that setting.");
            return false;
        }
        return true;
    }
}
