package com.entrocorp.linearlogic.oneinthegun.game;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.World;

import com.entrocorp.linearlogic.oneinthegun.OITG;

/**
 * A lightweight serializable Location wrapper
 */
public class SerializableLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    private double x;
    private double y;
    private double z;
    private String worldName;

    public SerializableLocation(Location loc) {
        if (loc == null)
            return;
        worldName = loc.getWorld() == null ? "null" : loc.getWorld().getName();
        x = loc.getX();
        y = loc.getY();
        z = loc.getZ();
    }

    public Location asBukkitLocation() {
        World world = OITG.instance.getServer().getWorld(worldName);
        return world == null ? null : new Location(world, x, y, z);
    }

    public boolean equals(Object loc) {
        if (!(loc instanceof SerializableLocation))
            return false;
        SerializableLocation compare = (SerializableLocation) loc;
        return compare.asBukkitLocation().equals(asBukkitLocation()) ? true : false;
    }
}
