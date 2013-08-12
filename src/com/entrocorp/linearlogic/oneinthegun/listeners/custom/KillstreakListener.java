package com.entrocorp.linearlogic.oneinthegun.listeners.custom;

import org.bukkit.entity.Player;

public interface KillstreakListener {

    public void onKillstreakChange(Player player, int oldStreak, int newStreak);
}
