package com.raafay;

import com.raafay.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    Map<String, String> eDateMap = new HashMap<>();
    Map<String, String> eDatingMap = new HashMap<>();
    Map<Player, Player> eDatingPlayerMap = new HashMap<>();

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("edate")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You are not allowed to execute this command!");
                return true;
            }
            Player p = (Player) sender;
            if (!p.hasPermission("edate.use")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to execute this command");
                return true;
            }
            if (args.length > 2) {
                p.sendMessage(ChatColor.RED + "Invalid command. Please type /edate to see the list of commands");
                return true;

            } else if (args.length == 0) {
                EDateHelp eh = new EDateHelp(p);
                eh.eDateHelpCommand();
                return true;

            } else if (args.length == 1) {
                Player target = getServer().getPlayerExact(args[0]);

                if (args[0].equalsIgnoreCase("check")) {
                    EDateCheck ec = new EDateCheck(p, eDatingPlayerMap);
                    ec.checkEDate();
                } else {
                    EDateTarget et = new EDateTarget(p, target, eDateMap, eDatingMap);
                    et.eDateTargetCommand();
                }
                return true;

            } else {
                Player target = getServer().getPlayerExact(args[1]);

                if (args[0].equalsIgnoreCase("accept")) {
                    EDateAccept ea = new EDateAccept(p, target, eDateMap, eDatingMap, eDatingPlayerMap);
                    ea.eDateAcceptCommand();
                    return true;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    EDateRemove er = new EDateRemove(p, target, eDateMap, eDatingMap, eDatingPlayerMap);
                    er.eDateRemoveCommand();
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid command. Please type /edate to see the list of commands");
                    return true;
                }
            }
        }
        return false;
    }
}
