package com.raafay.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EDateHelp {

    Player p;

    public EDateHelp(Player p) {

        this.p = p;
    }

    public void eDateHelpCommand() {
        
    p.sendMessage(ChatColor.YELLOW + "EDate Command List:");
    p.sendMessage("");
    p.sendMessage(ChatColor.AQUA + "/edate " + ChatColor.BLUE + "(shows a list of commands from the EDate plugin)");
    p.sendMessage(ChatColor.AQUA + "/edate <target> " + ChatColor.BLUE + "(edate a specific person)");
    p.sendMessage(ChatColor.AQUA + "/edate accept <target> " + ChatColor.BLUE + "(accept an edate request " +
            "from a specific person)");
    p.sendMessage(ChatColor.AQUA + "/edate remove <target> " + ChatColor.BLUE +
            "(remove your edate)");
    p.sendMessage(ChatColor.AQUA + "/edate check " + ChatColor.BLUE + "(check who you are currently edating)");

    }
}
