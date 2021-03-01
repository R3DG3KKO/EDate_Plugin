package com.raafay.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class EDateCheck {

    Player p;
    Map<Player, Player> edatingPlayerMap;

    public EDateCheck(Player p, Map<Player, Player> edatingPlayerMap) {

        this.p = p;
        this.edatingPlayerMap = edatingPlayerMap;
    }

    public void checkEDate() {

        try {
            Player target = edatingPlayerMap.get(p);

            p.sendMessage(ChatColor.YELLOW + "You are edating " + ChatColor.WHITE + target.getDisplayName());
        } catch (NullPointerException e) {
            p.sendMessage(ChatColor.YELLOW + "You are not edating anyone!");
        }
    }
}
