package com.entrocorp.linearlogic.oneinthechamber.game;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;

public class Arena {

    private String name;

    private int playerLimit;
    private int timeLimit;
    private int killLimit;

    private SerializableLocation lobby;
    private ArrayList<SerializableLocation> spawns;
    private ArrayList<SerializableLocation> signLocations;

    public Arena(String name) {
        this.name = name;
        init();
    }

    public void init() {
        if (playerLimit < 2)
            playerLimit = 10;
        if (timeLimit < 2) // Ten seconds
            timeLimit = 120;
        if (killLimit != -1 && killLimit < 1)
            killLimit = 10;
        if (spawns == null)
            spawns = new ArrayList<SerializableLocation>();
        if (signLocations == null)
            signLocations = new ArrayList<SerializableLocation>();
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerLimit() {
        return playerLimit;
    }

    public void setPlayerLimit(int limit) {
        this.playerLimit = limit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int limit) {
        timeLimit = limit;
    }

    public int getKillLimit() {
        return killLimit;
    }

    public void setKillLimit(int limit) {
        killLimit = limit;
    }

    public Location getLobby() {
        return lobby.asBukkitLocation();
    }

    public void setLobby(Location loc) {
        lobby = new SerializableLocation(loc);
    }

    public Location[] getSpawns() {
        Location[] output = new Location[spawns.size()];
        for (int i = 0; i < output.length; i++)
            output[i] = spawns.get(i).asBukkitLocation();
        return output;
    }

    public Location getRandomSpawn() {
        return spawns.get(new Random().nextInt(spawns.size())).asBukkitLocation();
    }

    public boolean isSpawn(Location loc) {
        return spawns.contains(new SerializableLocation(loc));
    }

    public void addSpawn(Location loc) {
        spawns.add(new SerializableLocation(loc));
    }

    public void clearSpawns() {
        spawns.clear();
    }

    public Location[] getSignLocations() {
        Location[] output = new Location[signLocations.size()];
        for (int i = 0; i < output.length; i++)
            output[i] = signLocations.get(i).asBukkitLocation();
        return output;
    }

    public boolean isSignLocation(Location loc) {
        return signLocations.contains(new SerializableLocation(loc));
    }

    public void addSignLocation(Location loc) {
        signLocations.add(new SerializableLocation(loc));
    }

    public boolean removeSignLocation(Location loc) {
        return signLocations.remove(new SerializableLocation(loc));
    }

    public void clearSignLocations() {
        signLocations.clear();
    }
}
