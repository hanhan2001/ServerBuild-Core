package me.xiaoying.serverbuild.script;

import org.bukkit.entity.Player;

/**
 * Script
 */
public interface Script {
    String getName();

    void performCommand(Player player, String[] args);

    boolean processFirst();
}