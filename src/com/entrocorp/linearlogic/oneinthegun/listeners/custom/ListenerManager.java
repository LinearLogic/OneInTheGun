package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<KillstreakListener> killstreakListeners = new ArrayList<KillstreakListener>();
    private List<VictoryListener> victoryListeners = new ArrayList<VictoryListener>();

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
