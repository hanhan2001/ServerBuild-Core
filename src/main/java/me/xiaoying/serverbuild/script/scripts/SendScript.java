package me.xiaoying.serverbuild.script.scripts;

import me.xiaoying.serverbuild.script.Script;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Script send
 */
public class SendScript implements Script {
    @Override
    public String getName() {
        return "send";
    }

    @Override
    public void performCommand(Player player, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        Player findPlayer;

        if (args.length == 0) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c错误的命令格式，应当为 &esend [player] 内容"));
            return;
        }

        findPlayer = Bukkit.getServer().getPlayer(args[0]);
        if (findPlayer == null)
            findPlayer = player;

        for (int i = 0; i < args.length; i++) {
            if (findPlayer != null && i == 0)
                continue;

            stringBuilder.append(args[i]);

            if (i == args.length - 1)
                break;

            stringBuilder.append(" ");
        }

        findPlayer.sendMessage(stringBuilder.toString());
    }

    @Override
    public boolean processFirst() {
        return false;
    }
}