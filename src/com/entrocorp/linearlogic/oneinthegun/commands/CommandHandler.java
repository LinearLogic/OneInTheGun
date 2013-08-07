package com.entrocorp.linearlogic.oneinthegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.entrocorp.linearlogic.oneinthegun.OITG;

public class CommandHandler implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(OITG.prefix + "Running OneInTheGun version " + ChatColor.LIGHT_PURPLE +
                    OITG.instance.getDescription().getVersion() + ChatColor.GRAY + " by LinearLogic. Type " +
                    ChatColor.AQUA + "/oitg help" + ChatColor.GRAY + " for assistance.");
            return true;
        }

        label = args[0];
        String[] newArgs = new String[args.length - 1];
        for (int i = 0; i < newArgs.length; i++)
            newArgs[i] = args[i + 1];
        
        OITGCommand cmd = null;
        // TODO initialize cmd based on command name

        if (cmd == null)
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Command not recognized.");
        else
            if (cmd.authorizeSender())
                cmd.run();
        return true;
    }
}
