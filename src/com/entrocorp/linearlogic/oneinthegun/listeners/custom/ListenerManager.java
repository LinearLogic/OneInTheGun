package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class ListenerManager {

    private List<KillstreakListener> killstreakListeners = new ArrayList<KillstreakListener>();
    private List<VictoryListener> victoryListeners = new ArrayList<VictoryListener>();

    public void loadListeners() {
        File listenerDir = new File(OITG.instance.getDataFolder() + File.separator + "listeners");
        listenerDir.mkdirs();
        ClassLoader cl;
        try {
            cl = new URLClassLoader(new URL[] {listenerDir.toURI().toURL()}, KillstreakListener.class.getClassLoader());
        } catch (MalformedURLException e) {
            OITG.instance.logSevere("Error while loading custom listeners: failed to resolve URL for class loader.");
            return;
        }
        for (File file : listenerDir.listFiles()) {
            if (!file.getName().endsWith(".class"))
                continue;
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            @SuppressWarnings("rawtypes")
            Class clazz = null;
            try {
                clazz = cl.loadClass(fileName);
            } catch (ClassNotFoundException e) {
                OITG.instance.logWarning("Failed to load custom listener \"" + fileName + "\".");
                e.printStackTrace();
                continue;
            }
            Object listener;
            try {
                listener = clazz.newInstance();
            } catch (InstantiationException e) {
                OITG.instance.logWarning("Failed to load custom listener \"" + fileName + "\".");
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                OITG.instance.logWarning("Failed to load custom listener \"" + fileName + "\".");
                e.printStackTrace();
                continue;
            }
            if (listener instanceof KillstreakListener) {
                killstreakListeners.add((KillstreakListener) listener);
                OITG.instance.logInfo("Loaded killstreak listener: " + listener.getClass().getName());
                continue;
            }
            if (listener instanceof VictoryListener) {
                victoryListeners.add((VictoryListener) listener);
                OITG.instance.logInfo("Loaded victory listener: " + listener.getClass().getName());
                continue;
            }
            OITG.instance.logWarning("Failed to load custom listener \"" + fileName + "\" - class does not " +
                    "implement KillstreakListener or VictoryListener.");
        }
    }

    public void fireKillstreakChangeEvent(final Player player, final int from, final int to) {
        for (KillstreakListener kl : killstreakListeners)
            kl.onKillstreakChange(player, from, to);
        OITG.instance.getServer().getScheduler().scheduleSyncDelayedTask(OITG.instance, new Runnable() {
            public void run() {
                OITG.instance.getServer().getPluginManager().callEvent(new KillstreakChangeEvent(player, from, to));
            }
        });
    }

    public void fireVictoryEvent(final Player player) {
        for (VictoryListener vl : victoryListeners)
            vl.onVictory(player);
        OITG.instance.getServer().getScheduler().scheduleSyncDelayedTask(OITG.instance, new Runnable() {
            public void run() {
                OITG.instance.getServer().getPluginManager().callEvent(new VictoryEvent(player));
            }
        });
    }

    public KillstreakListener[] getKillstreakListeners() {
        return killstreakListeners.toArray(new KillstreakListener[killstreakListeners.size()]);
    }

    public VictoryListener[] getVictoryListeners() {
        return victoryListeners.toArray(new VictoryListener[victoryListeners.size()]);
    }

    public void clearKillstreakListeners() {
        killstreakListeners.clear();
    }

    public void clearVictoryListeners() {
        victoryListeners.clear();
    }
}
