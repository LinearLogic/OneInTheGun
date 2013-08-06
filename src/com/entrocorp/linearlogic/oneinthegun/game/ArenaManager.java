package com.entrocorp.linearlogic.oneinthegun.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class ArenaManager {

    private ArrayList<Arena> arenas = new ArrayList<Arena>();

    public void loadArenas() {
        File arenaDir = new File(OITG.instance.getDataFolder() + File.separator + "arenas");
        arenaDir.mkdirs();
        for (File arenaFile : arenaDir.listFiles()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arenaFile));
                Arena arena = (Arena) ois.readObject();
                arena.init();
                arenas.add(arena);
                ois.close();
                OITG.instance.logInfo("Loaded arena: " + arena.toString());
            } catch (Exception e) {
                OITG.instance.logSevere("Failed to load an arena from the following file: " + arenaFile.getName());
                e.printStackTrace();
                continue;
            }
        }
    }

    public void saveArenas() {
        for (Arena arena : arenas)
            arena.save();
    }
}
