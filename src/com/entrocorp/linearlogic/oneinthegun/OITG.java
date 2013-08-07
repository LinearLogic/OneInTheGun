package com.entrocorp.linearlogic.oneinthegun;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.entrocorp.linearlogic.oneinthegun.events.GameListener;
import com.entrocorp.linearlogic.oneinthegun.events.GeneralListener;
import com.entrocorp.linearlogic.oneinthegun.game.ArenaManager;

public class OITG extends JavaPlugin {

    public static OITG instance;
    public static String prefix;

    private ArenaManager am;
    private GameListener gameListener;
    private GeneralListener generalListener;

    public void onEnable() {
        instance = this;
        am = new ArenaManager();
        logInfo("Loading the config...");
        saveDefaultConfig();
        prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + (getConfig().getBoolean("use-abbreviated-prefix") ? "OITG" : "One" +
                ChatColor.DARK_GRAY + "InThe" + ChatColor.GOLD + "Gun") + ChatColor.GRAY + "] ";
        am.setGlobalLobby(new Location(getServer().getWorld(getConfig().getString("global-lobby.world")),
                getConfig().getDouble("global-lobby.x"), getConfig().getDouble("global-lobby.y"), getConfig().getDouble("global-lobby.z"),
                (float) getConfig().getDouble("global-lobby.yaw"), (float) getConfig().getDouble("global-lobby.pitch")), false);
        logInfo("Registering listeners...");
        gameListener = new GameListener();
        generalListener = new GeneralListener();
        getServer().getPluginManager().registerEvents(generalListener, instance);
        logInfo("Loading arenas...");
        am.loadArenas();
        logInfo("Successfully enabled. Game on!");
    }

    public void onDisable() {
        gameListener.setRegistered(false);
        gameListener = null;
        HandlerList.unregisterAll(generalListener);
        generalListener = null;
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

    public GameListener getGameListener() {
        return gameListener;
    }

    public GeneralListener getGeneralListener() {
        return generalListener;
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
