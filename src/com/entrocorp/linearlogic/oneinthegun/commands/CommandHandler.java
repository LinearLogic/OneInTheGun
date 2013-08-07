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

        label = args[0].toLowerCase();
        String[] newArgs = new String[args.length - 1];
        for (int i = 0; i < newArgs.length; i++)
            newArgs[i] = args[i + 1];

        OITGCommand cmd = null;
        if (label.equals("create"))
            cmd = new CommandCreate(sender, args);
        else if (label.equals("leave"))
            cmd = new CommandLeave(sender, args);
        else if (label.equals("reload"))
            cmd = new CommandReload(sender, args);
        else if (label.equals("version"))
            cmd = new CommandVersion(sender, args);

        if (cmd == null) {
            sender.sendMessage(OITG.prefix + ChatColor.RED + "Command not recognized.");
            return true;
        }
        if (!cmd.checkArgsLength())
            return true;
        if (cmd instanceof OITGArenaCommand && !((OITGArenaCommand) cmd).validateArena())
            return true;
        if (cmd.authorizeSender())
            cmd.run();
        return true;
    }
}
