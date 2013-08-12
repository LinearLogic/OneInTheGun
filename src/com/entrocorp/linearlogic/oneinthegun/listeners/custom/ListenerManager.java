package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ListenerManager {

    private List<KillstreakListener> killstreakListeners = new ArrayList<KillstreakListener>();
    private List<VictoryListener> victoryListeners = new ArrayList<VictoryListener>();

    public void fireKillstreakChangeEvent(Player player, int oldStreak, int newStreak) {
        for (KillstreakListener kl : killstreakListeners)
            kl.onKillstreakChange(player, oldStreak, newStreak);
    }

    public void fireVictoryEvent(Player player) {
        for (VictoryListener vl : victoryListeners)
            vl.onVictory(player);
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
