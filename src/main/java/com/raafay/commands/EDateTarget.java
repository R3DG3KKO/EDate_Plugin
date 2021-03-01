package com.raafay.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class EDateTarget {

    Player p;
    Player target;
    Map<String, String> edateMap;
    Map<String, String> edatingMap;

    public EDateTarget(Player p, Player target, Map<String, String> edateMap, Map<String, String> edatingMap) {

        this.p = p;
        this.target = target;
        this.edateMap = edateMap;
        this.edatingMap = edatingMap;
    }

    public void eDateTargetCommand() {

        if (target == null) {
            p.sendMessage(ChatColor.RED + "Invalid command. Please type /edate to see the list of commands");
        } else if (target.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
            p.sendMessage(ChatColor.DARK_GRAY + "Silly, you can't edate yourself!");
        } else if (isPlayerEDating()) {
            p.sendMessage(ChatColor.RED + "You are already EDating someone!");
        } else if (isTargetEDating()) {
            p.sendMessage(ChatColor.RED + target.getDisplayName() + " is already EDating someone!");
        } else {
            if (!isPlayerSendingRequest()) {
                target.sendMessage(p.getDisplayName() + ChatColor.GREEN + " has asked you out on an edate!");
                target.sendMessage(ChatColor.GREEN + "type /edate accept " + ChatColor.WHITE + p.getDisplayName());
                p.sendMessage(ChatColor.GREEN + "You have sent " + ChatColor.WHITE + target.getDisplayName()
                              + ChatColor.GREEN + " an edate request!");
                edateMap.put(p.getUniqueId().toString(), target.getUniqueId().toString());
            } else {
                p.sendMessage(ChatColor.RED + "You have already sent this person an edate request");
            }
        }
    }

    public boolean isPlayerSendingRequest() {

        boolean isPEDatingTarget = false;

        try {
            if (edateMap.get(p.getUniqueId().toString()).equals(target.getUniqueId().toString())) {
                    isPEDatingTarget = true;
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
        return isPEDatingTarget;
    }

    public boolean isPlayerEDating() {

        boolean isPlayerEDating = false;

        if (edatingMap.containsKey(p.getUniqueId().toString()) ||
            edatingMap.containsValue(p.getUniqueId().toString())) {

            isPlayerEDating = true;
        }

        return isPlayerEDating;
    }

    public boolean isTargetEDating() {

        boolean isTargetEDating = false;

        if (edatingMap.containsKey(target.getUniqueId().toString()) ||
                edatingMap.containsValue(target.getUniqueId().toString())) {

            isTargetEDating = true;
        }

        return isTargetEDating;
    }
}
