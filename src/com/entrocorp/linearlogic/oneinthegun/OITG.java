package com.entrocorp.linearlogic.oneinthegun;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class OITG extends JavaPlugin {

    public static OITG instance;
    public static String prefix;

    public void onEnable() {
        instance = this;
        logInfo("Loading the config...");
        saveDefaultConfig();
        prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + (getConfig().getBoolean("use-abbreviated-prefix") ? "OITG" : "One" +
                ChatColor.DARK_GRAY + "InThe" + ChatColor.GOLD + "Gun") + ChatColor.GRAY + "] ";
        logInfo("Successfully enabled. Game on!");
    }

    public void onDisable() {
        logInfo("Saving the config...");
        saveConfig();
        logInfo("Successfully disabled. Game over!");
        instance = null;
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
