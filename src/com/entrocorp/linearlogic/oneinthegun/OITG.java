package com.entrocorp.linearlogic.oneinthegun;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.entrocorp.linearlogic.oneinthegun.game.ArenaManager;

public class OITG extends JavaPlugin {

    public static OITG instance;
    public static String prefix;

    private ArenaManager am;

    public void onEnable() {
        instance = this;
        am = new ArenaManager();
        logInfo("Loading the config...");
        saveDefaultConfig();
        prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + (getConfig().getBoolean("use-abbreviated-prefix") ? "OITG" : "One" +
                ChatColor.DARK_GRAY + "InThe" + ChatColor.GOLD + "Gun") + ChatColor.GRAY + "] ";
        logInfo("Loading arenas...");
        am.loadArenas();
        logInfo("Successfully enabled. Game on!");
    }

    public void onDisable() {
        logInfo("Saving the config...");
        saveConfig();
        logInfo("Saving arenas...");
        am.saveArenas();
        am = null;
        logInfo("Successfully disabled. Game over!");
        instance = null;
    }

    public ArenaManager getArenaManager() {
        return am;
    }

    public void logInfo(String msg) {
        getLogger().info(msg);
    }

    public void logWarning(String msg) {
        getLogger().warning(msg);
    }

    public void logSevere(String msg) {
        getLogger().severe(msg);
    }
}
