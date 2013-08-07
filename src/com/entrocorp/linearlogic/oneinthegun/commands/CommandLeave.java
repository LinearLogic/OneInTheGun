package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.entrocorp.linearlogic.oneinthegun.OITG;
import com.entrocorp.linearlogic.oneinthegun.game.Arena;

public class CommandLeave extends OITGCommand {

    public CommandLeave(CommandSender sender, String[] args) {
        super(sender, args, 0, "leave", null, true);
    }

    public void run() {
        Player player = (Player) sender;
        Arena arena = OITG.instance.getArenaManager().getArena(player);
        if (arena == null) {
            player.sendMessage(OITG.prefix + "You aren't in an arena.");
            return;
        }
        arena.removePlayer(player);
        player.teleport(OITG.instance.getArenaManager().getGlobalLobby());
        player.sendMessage(OITG.prefix + "You have left arena " + arena.toString() + ".");
    }
}
