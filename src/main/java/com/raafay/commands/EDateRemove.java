package com.raafay.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class EDateRemove {

    Player p;
    Player target;
    Map<String, String> edateMap;
    Map<String, String> edatingMap;
    Map<Player, Player> edatingPlayerMap;

    public EDateRemove(Player p, Player target, Map<String, String> edateMap, Map<String, String> edatingMap,
                       Map<Player, Player> edatingPlayerMap) {

        this.p = p;
        this.target = target;
        this.edateMap = edateMap;
        this.edatingMap = edatingMap;
        this.edatingPlayerMap = edatingPlayerMap;
    }

    public void eDateRemoveCommand() {

        if (target == null) {
            p.sendMessage(ChatColor.RED + "Invalid command. Please type /edate to see the list of commands");
        } else if (target.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
            p.sendMessage(ChatColor.DARK_GRAY + "Silly, you can't edate yourself!");
        } else if (!checkIfEDating()) {
            p.sendMessage(ChatColor.RED + "you are not EDating " + target.getDisplayName() + "!");
        } else {
            try {
                edatingMap.remove(p.getUniqueId().toString(), target.getUniqueId().toString());
            } catch (Exception e) {
                System.out.println();
            }
            try {
                edatingMap.remove(target.getUniqueId().toString(), p.getUniqueId().toString());
            } catch (Exception e) {
                System.out.println();
            }
            try {
                edatingPlayerMap.remove(p, target);
            } catch (Exception e) {
                System.out.println();
            }
            try {
                edatingPlayerMap.remove(target, p);
            } catch (Exception e) {
                System.out.println();
            }

            p.sendMessage(ChatColor.YELLOW + "You have removed " + target.getDisplayName() + " as your edate!");
            target.sendMessage(ChatColor.YELLOW + p.getDisplayName() + " has removed you as their edate!");
        }
    }

    public boolean checkIfEDating() {

        boolean isEDating = false;

        try {
            if (edatingMap.get(target.getUniqueId().toString()).equals(p.getUniqueId().toString())) {
                isEDating = true;
            }
        } catch (NullPointerException e) {
            System.out.println();
        }

        try {
            if (edatingMap.get(p.getUniqueId().toString()).equals(target.getUniqueId().toString())) {
                isEDating = true;
            }
        } catch (NullPointerException e) {
            System.out.println();
        }

        return isEDating;
    }
}
