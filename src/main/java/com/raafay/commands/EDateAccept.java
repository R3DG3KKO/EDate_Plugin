package com.raafay.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class EDateAccept {

    Player p;
    Player target;
    Map<String, String> edateMap;
    Map<String, String> edatingMap;
    Map<Player, Player> edatingPlayerMap;

    public EDateAccept(Player p, Player target, Map<String, String> edateMap, Map<String, String> edatingMap,
    Map<Player, Player> edatingPlayerMap) {

        this.p = p;
        this.target = target;
        this.edateMap = edateMap;
        this.edatingMap = edatingMap;
        this.edatingPlayerMap = edatingPlayerMap;
    }

    public void eDateAcceptCommand() {

        if (target == null) {
            p.sendMessage(ChatColor.RED + "Invalid command. Please type /edate to see the list of commands");
        } else if (target.getUniqueId().toString().equalsIgnoreCase(p.getUniqueId().toString())) {
            p.sendMessage(ChatColor.DARK_GRAY + "Silly, you can't edate yourself!");
        } else if (!ifPlayerHasRequest()) {
            p.sendMessage(target.getDisplayName() + ChatColor.GRAY + " has not sent you an edate request!");
        } else {
            edatingMap.put(p.getUniqueId().toString(), target.getUniqueId().toString());
            edateMap.remove(target.getUniqueId().toString(), p.getUniqueId().toString());

            try {
                edateMap.remove(p.getUniqueId().toString(), target.getUniqueId().toString());
            } catch (NullPointerException e) {
                System.out.println();
            }

            edatingPlayerMap.put(p, target);
            edatingPlayerMap.put(target, p);

            try {
                edateMap.remove(p.getUniqueId().toString(), target.getUniqueId().toString());
            } catch (NullPointerException e) {
                System.out.println();
            }

            p.sendMessage(ChatColor.GREEN + "Congratulations! You and " + target.getDisplayName() +
                    ChatColor.GREEN + " are now edating!");
            target.sendMessage(ChatColor.GREEN + "Congratulations! " + ChatColor.WHITE + p.getDisplayName()
                               + " has accepted " + ChatColor.GREEN + "your edate request!");
        }
    }

    public boolean ifPlayerHasRequest() {

        boolean ifPHasReq = false;

        try {
            if (edateMap.get(target.getUniqueId().toString()).equals(p.getUniqueId().toString())) {
                ifPHasReq = true;
            }
        } catch (NullPointerException e) {
            System.out.println();
        }
        return ifPHasReq;
    }
}
