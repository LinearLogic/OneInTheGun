package com.entrocorp.linearlogic.oneinthegun;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.entrocorp.linearlogic.oneinthegun.commands.OITGCommandHandler;
import com.entrocorp.linearlogic.oneinthegun.game.ArenaManager;
import com.entrocorp.linearlogic.oneinthegun.listeners.ListenerManager;

public class OITG extends JavaPlugin {

    public static OITG instance;

    public static String prefix;
    public static boolean joinToLobby;
    public static boolean killstreaks;
    public static boolean saveOnEdit;
    public static int pregameDuration;
    public static int spawnShieldDuration;

    private ArenaManager am;
    private ListenerManager lm;
    private OITGCommandHandler ch;

    @Override
    public void onEnable() {
        instance = this;
        am = new ArenaManager();
        logInfo("Loading the config...");
        saveDefaultConfig();
        reloadConfig();
        logInfo("Registering command handler...");
        ch = new OITGCommandHandler();
        getCommand("oitg").setExecutor(ch);
        logInfo("Registering listeners...");
        lm = new ListenerManager();
        lm.loadListeners();
        logInfo("Loading arenas...");
        am.loadArenas();
        logInfo("Successfully enabled. Game on!");
    }

    @Override
    public void onDisable() {
        ch = null;
        lm.getGameListener().setRegistered(false);
        HandlerList.unregisterAll(lm.getGeneralListener());
        lm.clearKillstreakListeners();
        lm.clearVictoryListeners();
        logInfo("Saving the config...");
        saveConfig();
        logInfo("Saving arenas...");
        am.shutdown();
        lm = null;
        am = null;
        logInfo("Successfully disabled. Game over!");
        instance = null;
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + (getConfig().getBoolean("use-abbreviated-prefix") ? "OITG" : "One" +
                ChatColor.DARK_GRAY + "InThe" + ChatColor.GOLD + "Gun") + ChatColor.GRAY + "] ";
        joinToLobby = getConfig().getBoolean("join-to-lobby");
        killstreaks = getConfig().getBoolean("killstreaks");
        saveOnEdit = getConfig().getBoolean("save-arena-on-edit");
        pregameDuration = getConfig().getInt("timers.pregame-countdown");
        spawnShieldDuration = getConfig().getInt("timers.spawn-shield");
        am.setGlobalLobby(new Location(getServer().getWorld(getConfig().getString("global-lobby.world")),
                getConfig().getDouble("global-lobby.x"), getConfig().getDouble("global-lobby.y"), getConfig().getDouble("global-lobby.z"),
                (float) getConfig().getDouble("global-lobby.yaw"), (float) getConfig().getDouble("global-lobby.pitch")), false);
    }

    public ArenaManager getArenaManager() {
        return am;
    }

    public ListenerManager getListenerManager() {
        return lm;
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
